package com.jdc.shop.api.member.output;

import java.util.List;

import com.jdc.shop.model.entity.Sale;

import lombok.Data;

@Data
public class PurchaseDetailsDto {
	
	private PurchaseDto purchase;
	private List<PurchaseItem> items;
	
	public static PurchaseDetailsDto from(Sale entity) {
		var dto = new PurchaseDetailsDto();
		dto.setPurchase(PurchaseDto.from(entity));
		dto.setItems(entity.getItems().stream().map(PurchaseItem::from).toList());
		return dto;
	}

}