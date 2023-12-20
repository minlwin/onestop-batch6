package com.jdc.shop.api.member.output;

import com.jdc.shop.api.anonymous.output.CatalogDetailsDto;
import com.jdc.shop.model.entity.SaleItem;
import com.jdc.shop.model.entity.pk.SaleItemPk;

import lombok.Data;

@Data
public class PurchaseItem {

	private SaleItemPk id;
	private CatalogDetailsDto item;
	
	public static PurchaseItem from(SaleItem entity) {
		var dto = new PurchaseItem();
		dto.setId(entity.getId());
		dto.setItem(CatalogDetailsDto.from(entity.getCatalog()));
		return dto;
	}
}