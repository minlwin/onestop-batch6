package com.jdc.shop.api.owner.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GlodPriceDto {

	private LocalDateTime id;

	private long idValue;

	private BigDecimal salePriceFor16P;

	private BigDecimal buyPriceFor16P;

	private BigDecimal salePriceFor15P;

	private BigDecimal buyPriceFor15P;

}