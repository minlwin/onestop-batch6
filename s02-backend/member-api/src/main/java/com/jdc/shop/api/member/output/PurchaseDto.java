package com.jdc.shop.api.member.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.shop.model.entity.pk.SalePk;

import lombok.Data;

@Data
public class PurchaseDto {

	private SalePk id;

	private LocalDateTime issueAt;

	private int itemCount;

	private BigDecimal total;

}