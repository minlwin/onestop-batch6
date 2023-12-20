package com.jdc.shop.api.employee.output;

import com.jdc.shop.api.anonymous.output.CatalogDetailsDto;
import com.jdc.shop.model.entity.SaleItem;
import com.jdc.shop.model.entity.pk.SaleItemPk;

import lombok.Data;

@Data
public class SaleItemDto {

	private SaleItemPk id;
	private CatalogDetailsDto catalog;
	
	public static SaleItemDto from(SaleItem entity) {
		var dto = new SaleItemDto();
		dto.setId(entity.getId());
		dto.setCatalog(CatalogDetailsDto.from(entity.getCatalog()));
		return dto;
	}
}