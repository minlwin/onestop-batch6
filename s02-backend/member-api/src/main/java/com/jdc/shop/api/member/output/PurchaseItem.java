package com.jdc.shop.api.member.output;

import com.jdc.shop.api.anonymous.output.CatalogDetailsDto;
import com.jdc.shop.model.entity.pk.SaleItemPk;

import lombok.Data;

@Data
public class PurchaseItem {

	private SaleItemPk id;

	private CatalogDetailsDto item;

}