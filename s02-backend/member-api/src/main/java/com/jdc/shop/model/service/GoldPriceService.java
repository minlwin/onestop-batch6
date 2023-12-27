package com.jdc.shop.model.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.api.owner.input.GlodPriceForm;
import com.jdc.shop.api.owner.input.GoldPriceSearch;
import com.jdc.shop.api.owner.output.GoldPriceDto;
import com.jdc.shop.model.entity.GoldPrice;
import com.jdc.shop.model.entity.GoldPrice_;
import com.jdc.shop.model.repo.GoldPriceRepo;
import com.jdc.shop.utils.exceptions.ApiBusinessException;
import com.jdc.shop.utils.io.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class GoldPriceService {
	
	@Autowired
	private GoldPriceRepo goldPriceRepo;
	
	@Autowired
	private CatalogPriceChangeScheduleService priceChangeService;

	public Page<GoldPriceDto> search(GoldPriceSearch form, int page, int size) {
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(GoldPrice.class);
			cq.select(cb.count(root.get(GoldPrice_.businessTime)));
			
			cq.where(form.where(cb, root));
			return cq;
		};
		
		Function<CriteriaBuilder, CriteriaQuery<GoldPriceDto>> queryFunc = cb -> {
			var cq = cb.createQuery(GoldPriceDto.class);
			var root = cq.from(GoldPrice.class);
			GoldPriceDto.select(cq, root);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.asc(root.get(GoldPrice_.businessTime)));
			
			return cq;
		};

		return goldPriceRepo.search(queryFunc, countFunc, page, size);
	}

	public GoldPriceDto findById(long id) {
		var businessTime = Instant.ofEpochMilli(id).atZone(ZoneId.systemDefault()).toLocalDateTime();
		return goldPriceRepo.findById(businessTime).map(GoldPriceDto::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid gold price id."));
	}

	@Transactional
	public DataModificationResult<Long> create(GlodPriceForm form) {

		if(form.getBusinessTime().isBefore(LocalDateTime.now())) {
			throw new ApiBusinessException("You can't create old data.");
		}
		
		var entity = goldPriceRepo.save(form.entity());
		
		priceChangeService.createSchedule(entity.getBusinessTime());
		
		var idValue = entity.getBusinessTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		
		return new DataModificationResult<Long>(idValue, "Gold Price has been created.");
	}

	@Transactional
	public DataModificationResult<Long> update(long id, GlodPriceForm form) {
		
		var businessTime = Instant.ofEpochMilli(id).atZone(ZoneId.systemDefault()).toLocalDateTime();
		var entity = goldPriceRepo.findById(businessTime)
				.orElseThrow(() -> new ApiBusinessException("Invalid gold price id."));
		
		if(form.getBusinessTime().isBefore(LocalDateTime.now())) {
			throw new ApiBusinessException("You can't update old data.");
		}
		
		entity.setBasePrice(form.getBasePrice());
		entity.setDiffFor15P(form.getDiffFor15P());
		entity.setDiffFor16P(form.getDiffFor16P());
		
		var idValue = entity.getBusinessTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		return new DataModificationResult<Long>(idValue, "Gold Price has been updated.");
	}

}
