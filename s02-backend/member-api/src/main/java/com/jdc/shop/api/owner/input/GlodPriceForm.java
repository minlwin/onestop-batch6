package com.jdc.shop.api.owner.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GlodPriceForm {

	private LocalDateTime businessTime;

	private BigDecimal basePrice;

	private BigDecimal diffFor16P;

	private BigDecimal diffFor15P;

}