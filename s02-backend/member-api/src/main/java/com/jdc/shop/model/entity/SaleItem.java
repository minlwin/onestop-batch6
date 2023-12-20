package com.jdc.shop.model.entity;

import com.jdc.shop.model.entity.pk.SaleItemPk;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SALE_ITEM")
public class SaleItem {

	@EmbeddedId
	private SaleItemPk id = new SaleItemPk();

	@ManyToOne
	@JoinColumn(name = "catalog_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Catalog catalog;

	@ManyToOne
	@JoinColumn(name = "sale_date", referencedColumnName = "sale_date", insertable = false, updatable = false)
	@JoinColumn(name = "sale_seq", referencedColumnName = "sale_seq", insertable = false, updatable = false)
	private Sale sale;
	
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
		id.setCatalogId(catalog.getId());
	}

}