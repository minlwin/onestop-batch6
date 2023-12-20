package com.jdc.shop.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "GOLD_PRICE")
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
	
	private Status status;
	
	public enum Status {
		Created, Calculated
	}

}