package com.jdc.shop.api.anonymous.output;

import java.math.BigDecimal;
import java.util.List;

import com.jdc.shop.model.constants.Purity;

import lombok.Data;

@Data
public class CatalogDetailsDto {

	private CatalogDto baseInfo;

	private List<String> images;

	private Purity purity;

	private GoldWeight weight;

	private BigDecimal lostWeight;

}