package com.jdc.shop.model.entity;

import com.jdc.shop.model.entity.pk.SaleItemPk;

import lombok.Data;

@Data
public class SaleItem {

	private SaleItemPk id;

	private Catalog catalog;

	private Sale sale;

}