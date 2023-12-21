package com.jdc.shop.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.api.anonymous.output.CatalogDetailsDto;
import com.jdc.shop.api.anonymous.output.CatalogDto;
import com.jdc.shop.api.employee.input.CatalogForm;
import com.jdc.shop.api.employee.input.CatalogSearch;
import com.jdc.shop.api.owner.output.GoldPriceDto;
import com.jdc.shop.model.entity.Catalog;
import com.jdc.shop.model.entity.Catalog_;
import com.jdc.shop.model.entity.GoldPrice;
import com.jdc.shop.model.entity.GoldPrice_;
import com.jdc.shop.model.repo.CatalogRepo;
import com.jdc.shop.model.repo.CategoryRepo;
import com.jdc.shop.model.repo.GoldPriceRepo;
import com.jdc.shop.utils.exceptions.ApiBusinessException;
import com.jdc.shop.utils.io.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class CatalogService {
	
	@Autowired
	private CatalogRepo catalogRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private GoldPriceRepo goldPriceRepo;
	
	@Autowired
	private CatalogPriceChangeService priceChangeService;
	
	@Autowired
	private PhotoUploadService photoUploadService;

	@Transactional(readOnly = true)
	public Page<CatalogDto> search(CatalogSearch form, int page, int size) {
		
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Catalog.class);
			cq.select(cb.count(root.get(Catalog_.id)));
			
			cq.where(form.where(cb, root));
			return cq;
		};
		
		Function<CriteriaBuilder, CriteriaQuery<CatalogDto>> queryFunc = cb -> {
			var cq = cb.createQuery(CatalogDto.class);
			var root = cq.from(Catalog.class);
			CatalogDto.select(cq, root);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.asc(root.get(Catalog_.name)));
			
			return cq;
		};

		return catalogRepo.search(queryFunc, countFunc, page, size);	
	}

	@Transactional(readOnly = true)
	public CatalogDetailsDto findById(int id) {
		return catalogRepo.findById(id).map(CatalogDetailsDto::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid catalog id."));
	}

	public DataModificationResult<Integer> create(CatalogForm form) {
		var goldPrice = findGoldPrice();
		var category = categoryRepo.findById(form.getCategoryId())
				.orElseThrow(() -> new ApiBusinessException("Invalid category id."));
		
		var entity = form.entity();
		entity.setCategory(category);
	
		entity = catalogRepo.saveAndFlush(entity);
		priceChangeService.calculate(entity.getId(), goldPrice);
		
		return new DataModificationResult<Integer>(entity.getId(), "Catalog has been created.");
	}

	public DataModificationResult<Integer> update(int id, CatalogForm form) {
		
		var goldPrice = findGoldPrice();
		var entity = catalogRepo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("Invalid catalog id."));
		var category = categoryRepo.findById(form.getCategoryId())
				.orElseThrow(() -> new ApiBusinessException("Invalid category id."));

		entity.setCategory(category);
		entity.setName(form.getName());
		entity.setDescription(form.getDescription());
		entity.setBasedPrice(form.getBasedPrice());
		entity.setPurity(form.getPurity());
		entity.setWeight(form.getWeight());
		entity.setLostWeight(form.getLostWeight());
		entity.setGoldSmithFees(form.getGoldSmithFees());
		
		entity = catalogRepo.saveAndFlush(entity);
		priceChangeService.calculate(entity.getId(), goldPrice);
		
		return new DataModificationResult<Integer>(entity.getId(), "Catalog has been updated.");
	}

	public DataModificationResult<Integer> uploadPhoto(int id, List<MultipartFile> files) {
		var entity = catalogRepo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("Invalid catalog id."));
		
		var images = photoUploadService.saveCatalogImages(id, files);
		entity.getImages().addAll(images);
		
		if(!StringUtils.hasLength(entity.getCoverImage()) && !entity.getImages().isEmpty()) {
			entity.setCoverImage(entity.getImages().get(0));
		}
		
		return new DataModificationResult<Integer>(entity.getId(), "Catalog has been updated.");
	}

	private GoldPriceDto findGoldPrice() {
		
		Function<CriteriaBuilder, CriteriaQuery<GoldPrice>> queryFunc = cb -> {
			var cq = cb.createQuery(GoldPrice.class);
			var root = cq.from(GoldPrice.class);
			
			cq.select(root);
			
			cq.where(
				cb.isFalse(root.get(GoldPrice_.deleted)),
				cb.lessThanOrEqualTo(root.get(GoldPrice_.businessTime), LocalDateTime.now())
			);
			
			cq.orderBy(cb.desc(root.get(GoldPrice_.businessTime)));
			
			return cq;
		};
		
		return goldPriceRepo.search(queryFunc, 1).stream()
				.map(GoldPriceDto::from)
				.findFirst()
				.orElseThrow(() -> new ApiBusinessException("There is no price setting at this moment."));				
	}

}
