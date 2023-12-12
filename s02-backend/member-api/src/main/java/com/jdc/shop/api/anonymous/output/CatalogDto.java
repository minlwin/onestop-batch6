package com.jdc.shop.api.anonymous.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CatalogDto {

	private long id;

	private String name;

	private int categoryId;

	private String categoryName;

	private String description;

	private BigDecimal price;

	private String coverImage;

	private LocalDateTime createAt;

	public boolean soldOut;

}