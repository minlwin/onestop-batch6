package com.jdc.shop.api.employee.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CatalogSearch {

	private int categoryId;

	private String name;

	private LocalDate createFrom;

	private BigDecimal priceFrom;

	private BigDecimal priceTo;

}