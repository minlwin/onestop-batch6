package com.jdc.shop.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GlodPrice extends AbstractEntity {

	private LocalDateTime businessTime;

	private BigDecimal basePrice;

	private BigDecimal diffFor16P;

	private BigDecimal diffFor15P;

}