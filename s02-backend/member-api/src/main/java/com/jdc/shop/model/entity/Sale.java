package com.jdc.shop.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jdc.shop.model.entity.pk.SalePk;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "SALE")
public class Sale extends AbstractEntity {

	@EmbeddedId
	private SalePk id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Employee sellBy;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Member customer;

	private LocalDateTime saleAt;

	private BigDecimal salePrice;

	private BigDecimal discount;
	
	@OneToMany(mappedBy = "sale", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<SaleItem> items = new ArrayList<>();
	
	public void addItem(SaleItem item) {
		item.setSale(this);
		items.add(item);
		item.getId().setSaleDate(this.getId().getSaleDate());
		item.getId().setSaleSeq(this.getId().getSaleSeq());
	}

}