package com.jdc.shop.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.api.employee.input.SaleForm;
import com.jdc.shop.api.employee.input.SaleSearch;
import com.jdc.shop.api.employee.output.SaleDetailsDto;
import com.jdc.shop.api.employee.output.SaleDto;
import com.jdc.shop.model.entity.Sale;
import com.jdc.shop.model.entity.SaleItem;
import com.jdc.shop.model.entity.Sale_;
import com.jdc.shop.model.entity.pk.SalePk;
import com.jdc.shop.model.repo.CatalogRepo;
import com.jdc.shop.model.repo.EmployeeRepo;
import com.jdc.shop.model.repo.MemberRepo;
import com.jdc.shop.model.repo.SaleItemRepo;
import com.jdc.shop.model.repo.SaleRepo;
import com.jdc.shop.utils.exceptions.ApiBusinessException;
import com.jdc.shop.utils.io.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class SaleService {
	
	@Autowired
	private SaleRepo repo;
	
	@Autowired
	private SaleIdGenerator idGenerator;
	
	@Autowired
	private MemberRepo memberRepo;
	@Autowired
	private CatalogRepo catalogRepo;
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private SaleItemRepo itemRepo;

	public Page<SaleDto> search(SaleSearch form, int page, int size) {
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Sale.class);
			cq.select(cb.count(root.get(Sale_.id)));
			
			cq.where(form.where(cb, root));
			return cq;
		};
		
		Function<CriteriaBuilder, CriteriaQuery<SaleDto>> queryFunc = cb -> {
			var cq = cb.createQuery(SaleDto.class);
			var root = cq.from(Sale.class);
			SaleDto.select(cq, root);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Sale_.saleAt)));
			
			return cq;
		};

		return repo.search(queryFunc, countFunc, page, size);
	}

	public SaleDetailsDto findById(String invoiceNumber) {
		var id = SalePk.from(invoiceNumber);
		return repo.findById(id).map(SaleDetailsDto::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid invoice number."));
	}

	@Transactional
	public DataModificationResult<String> create(SaleForm form) {
		
		var customer = memberRepo.findById(form.getCustomerId())
				.orElseThrow(() -> new ApiBusinessException("Invalid customer id."));
		
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		var employee = employeeRepo.findOneByAccountLoginId(username)
				.orElseThrow(() -> new ApiBusinessException("Invalid login id."));
				
		var catalogs = catalogRepo.findAllById(form.getItems());
		
		if(catalogs.isEmpty()) {
			throw new ApiBusinessException("There is no catalogs because of invalid catalog ids.");
		}
		
		var totalPrice = catalogs.stream().map(a -> a.getSalePrice())
				.reduce((a, b) -> a.add(b))
				.orElseThrow(() -> new ApiBusinessException("Invalid sale price for catalogs."));

		var entity = new Sale();
		entity.setId(idGenerator.generate(LocalDate.now()));
		entity.setSaleAt(LocalDateTime.now());
		entity.setSellBy(employee);
		entity.setCustomer(customer);	
		entity.setDiscount(form.getDiscount());
		entity.setSalePrice(totalPrice);
		
		for(var catalog : catalogs) {
			var item = new SaleItem();
			item.setCatalog(catalog);
			entity.addItem(item);
			catalog.setSoldOut(true);
		}
		
		entity = repo.save(entity);
		
		return new DataModificationResult<String>(entity.getId().getInvoiceNumber(), "Sale has beend created.");
	}

	@Transactional
	public DataModificationResult<String> update(String invoiceNumber, SaleForm form) {
		var catalogs = catalogRepo.findAllById(form.getItems());
		
		if(catalogs.isEmpty()) {
			throw new ApiBusinessException("There is no catalogs because of invalid catalog ids.");
		}
		
		var totalPrice = catalogs.stream().map(a -> a.getSalePrice())
				.reduce((a, b) -> a.add(b))
				.orElseThrow(() -> new ApiBusinessException("Invalid sale price for catalogs."));
		
		var id = SalePk.from(invoiceNumber);
		var entity = repo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("Invalid invoice number."));
		
		for(var item : entity.getItems()) {
			itemRepo.delete(item);
		}
		entity.getItems().clear();
		entity = repo.saveAndFlush(entity);
		
		for(var catalog : catalogs) {
			var item = new SaleItem();
			item.setCatalog(catalog);
			entity.addItem(item);
		}
		entity.setSalePrice(totalPrice);
		entity.setDiscount(form.getDiscount());
		entity = repo.saveAndFlush(entity);

		return new DataModificationResult<String>(entity.getId().getInvoiceNumber(), "Sale has beend updated.");
	}

}
