package com.jdc.shop.api.anonymous.input;

import lombok.Data;

@Data
public class CatalogSearchForm {

	private Integer categoryId;

	private String keyword;

}