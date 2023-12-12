package com.jdc.shop.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoldPrice extends AbstractEntity {

	@Id
	private LocalDateTime businessTime;

	@Column(nullable = false)
	private BigDecimal basePrice;

	@Column(nullable = false)
	private BigDecimal diffFor16P;

	@Column(nullable = false)
	private BigDecimal diffFor15P;

}