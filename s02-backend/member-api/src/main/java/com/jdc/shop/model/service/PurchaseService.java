package com.jdc.shop.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.api.member.input.PurchaseSearch;
import com.jdc.shop.api.member.output.PurchaseDetailsDto;
import com.jdc.shop.api.member.output.PurchaseDto;
import com.jdc.shop.model.entity.Member_;
import com.jdc.shop.model.entity.Sale;
import com.jdc.shop.model.entity.Sale_;
import com.jdc.shop.model.entity.pk.SalePk;
import com.jdc.shop.model.repo.SaleRepo;
import com.jdc.shop.utils.exceptions.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class PurchaseService {
	
	@Autowired
	private SaleRepo repo;

	public Page<PurchaseDto> search(PurchaseSearch form, int page, int size) {
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Sale.class);
			cq.select(cb.count(root.get(Sale_.id)));
			
			cq.where(form.where(cb, root));
			return cq;
		};
		
		Function<CriteriaBuilder, CriteriaQuery<PurchaseDto>> queryFunc = cb -> {
			var cq = cb.createQuery(PurchaseDto.class);
			var root = cq.from(Sale.class);
			PurchaseDto.select(cq, root);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Sale_.saleAt)));
			
			return cq;
		};

		return repo.search(queryFunc, countFunc, page, size);	
	}

	public PurchaseDetailsDto findById(String invoiceNumber) {
		var id = SalePk.from(invoiceNumber);
		return repo.findById(id).map(PurchaseDetailsDto::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid invoice number."));
	}

	public List<PurchaseDto> findByMemberId(int id) {
		return repo.search(cb -> {
			var cq = cb.createQuery(PurchaseDto.class);
			var root = cq.from(Sale.class);
			PurchaseDto.select(cq, root);
			cq.where(cb.equal(root.get(Sale_.customer).get(Member_.id), id));
			cq.orderBy(cb.desc(root.get(Sale_.saleAt)));
			return cq;
		});
	}

}
