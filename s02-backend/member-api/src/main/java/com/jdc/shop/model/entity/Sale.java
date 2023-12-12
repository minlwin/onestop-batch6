package com.jdc.shop.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.shop.model.entity.pk.SalePk;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Sale extends AbstractEntity {

	private SalePk id;

	private Employee sellBy;

	private Member customer;

	private LocalDateTime saleAt;

	private BigDecimal salePrice;

	private BigDecimal discount;

}