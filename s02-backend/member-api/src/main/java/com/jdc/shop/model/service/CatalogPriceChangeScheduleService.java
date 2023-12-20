package com.jdc.shop.model.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.api.owner.output.GoldPriceDto;
import com.jdc.shop.model.entity.GoldPrice.Status;
import com.jdc.shop.model.repo.CatalogRepo;
import com.jdc.shop.model.repo.GoldPriceRepo;
import com.jdc.shop.utils.exceptions.ApiBusinessException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CatalogPriceChangeScheduleService {

	private final TaskScheduler taskScheduler;
	private final Map<LocalDateTime, ScheduledFuture<?>> schedules;
	
	@Autowired
	private GoldPriceRepo priceRepo;
	@Autowired
	private CatalogRepo catalogRepo;
	
	@Autowired
	private CatalogPriceChangeService priceChangeService;
	
	public CatalogPriceChangeScheduleService(TaskScheduler taskScheduler) {
		this.taskScheduler = taskScheduler;
		schedules = Collections.synchronizedMap(new HashMap<>());
	}
	
	public void createSchedule(LocalDateTime businessTime) {
		if(Optional.ofNullable(schedules.get(businessTime)).isEmpty()) {
			var schedule = taskScheduler.schedule(() -> calculate(businessTime), 
					businessTime.atZone(ZoneId.systemDefault()).toInstant());
			schedules.put(businessTime, schedule);
		}
	}
	
	public void cancelSchedule(LocalDateTime businessTime) {
		Optional.ofNullable(schedules.get(businessTime)).ifPresent(schedule -> {
			if(!schedule.isCancelled()) {
				schedule.cancel(true);
				schedules.remove(businessTime);
			}
		});	
	}
	
	@Transactional
	public void calculate(LocalDateTime businessTime) {
		
		log.info("Calculation for {} is started.", businessTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

		var goldPrice = priceRepo.findById(businessTime)
				.orElseThrow(() -> new ApiBusinessException("Invalid gold price id."));
		
		var goldPriceDto = GoldPriceDto.from(goldPrice);
		
		var catalogsNeedToBeCalculated = catalogRepo.searchIdForPriceChange();
		
		log.info("{} catalogs are need to be calculated.", catalogsNeedToBeCalculated.size());
		
		for(var catalog : catalogsNeedToBeCalculated) {
			priceChangeService.calculate(catalog, goldPriceDto);
			log.info("Catalog id {} has been calculated.", catalog);
		}
		
		goldPrice.setStatus(Status.Calculated);
		
		schedules.remove(businessTime);
		
		log.info("Total {} catalogs have been calculated.", catalogsNeedToBeCalculated.size());
		log.info("Calculation for {} is ended.", businessTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
	}
}
