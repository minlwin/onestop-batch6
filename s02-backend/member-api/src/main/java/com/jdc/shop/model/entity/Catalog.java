package com.jdc.shop.model.entity;

import java.math.BigDecimal;
import java.util.List;

import com.jdc.shop.model.constants.Purity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Catalog extends AbstractEntity {

	private int id;

	private String name;

	private Category category;

	private String description;

	private BigDecimal basedPrice;

	private BigDecimal salePrice;

	private Purity purity;

	private BigDecimal wieght;

	private BigDecimal lostWeight;

	private BigDecimal goldSmithFees;

	private boolean soldOut;

	private String coverImage;

	private List<String> images;

}