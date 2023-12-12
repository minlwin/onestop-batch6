package com.jdc.shop.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.shop.model.entity.pk.SalePk;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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

}