package com.jdc.shop.api.employee.output;

import java.util.List;

import com.jdc.shop.model.entity.Sale;

import lombok.Data;

@Data
public class SaleDetailsDto {

	private SaleDto sale;
	private List<SaleItemDto> items;

	public static SaleDetailsDto from(Sale entity) {
		var dto = new SaleDetailsDto();
		dto.setSale(SaleDto.from(entity));
		dto.setItems(entity.getItems().stream().map(SaleItemDto::from).toList());
		return dto;
	}
}