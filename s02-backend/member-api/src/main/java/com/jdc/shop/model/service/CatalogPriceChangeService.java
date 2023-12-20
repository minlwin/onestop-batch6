package com.jdc.shop.model.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.api.owner.output.GoldPriceDto;
import com.jdc.shop.model.repo.CatalogRepo;

@Service
public class CatalogPriceChangeService {
	
	@Autowired
	private CatalogRepo catalogRepo;

	@Transactional
	public void calculate(Integer catalogId, GoldPriceDto goldPriceDto) {

		var catalog = catalogRepo.getReferenceById(catalogId);
		
		var priceForCalculation = switch (catalog.getPurity()){
		case Sixteen -> goldPriceDto.getSalePriceFor16P();
		case Fifteen -> goldPriceDto.getSalePriceFor15P();
		};

		var priceForOneYwae = priceForCalculation.divide(new BigDecimal(128), RoundingMode.HALF_UP);
		
		catalog.setMarketTime(goldPriceDto.getId());
		catalog.setMarketPrice(priceForCalculation);
		
		catalog.setNetPrice(catalog.getWeight().multiply(priceForOneYwae));
		catalog.setLostWeightFee(catalog.getLostWeight().multiply(priceForOneYwae));
		
		catalog.setSalePrice(catalog.getNetPrice().add(catalog.getLostWeightFee()).add(catalog.getGoldSmithFees()));
	}

}
