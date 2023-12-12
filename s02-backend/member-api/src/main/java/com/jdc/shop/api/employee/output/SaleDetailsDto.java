package com.jdc.shop.api.employee.output;

import lombok.Data;

@Data
public class SaleDetailsDto {

	private SaleDto sale;

	private SaleItemDto items;

}