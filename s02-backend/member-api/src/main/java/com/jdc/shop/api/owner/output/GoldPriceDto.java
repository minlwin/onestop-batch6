package com.jdc.shop.api.owner.output;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.jdc.shop.model.entity.GoldPrice;
import com.jdc.shop.model.entity.GoldPrice.Status;
import com.jdc.shop.model.entity.GoldPrice_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class GoldPriceDto {

	@Getter
	private LocalDateTime id;
	private BigDecimal basePrice;
	private BigDecimal diffFor16P;
	private BigDecimal diffFor15P;
	@Getter
	private Status status;
	
	
	public static void select(CriteriaQuery<GoldPriceDto> cq, Root<GoldPrice> root) {
		cq.multiselect(
			root.get(GoldPrice_.businessTime),
			root.get(GoldPrice_.basePrice),
			root.get(GoldPrice_.diffFor16P),
			root.get(GoldPrice_.diffFor15P),
			root.get(GoldPrice_.status)			
		);
	}
	
	public static GoldPriceDto from(GoldPrice entity) {
		return new GoldPriceDto(entity.getBusinessTime(), 
				entity.getBasePrice(), 
				entity.getDiffFor16P(), 
				entity.getDiffFor15P(), 
				entity.getStatus());
	}
	

	public BigDecimal getSalePriceFor16P() {
		return basePrice;
	}
	public BigDecimal getBuyPriceFor16P() {
		return basePrice.subtract(diffFor16P);
	}
	public BigDecimal getSalePriceFor15P() {
		return basePrice.divide(new BigDecimal(16), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(15));
	}
	public BigDecimal getBuyPriceFor15P() {
		return getSalePriceFor15P().subtract(diffFor15P);
	}
	public long getIdValue() {
		return id.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
	
}