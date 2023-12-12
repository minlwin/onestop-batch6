package com.jdc.shop.api.member.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.jdc.shop.model.entity.pk.SalePk;

import lombok.Data;

@Data
public class PurchaseDetailsDto {

	private SalePk id;

	private LocalDateTime issueAt;

	private BigDecimal total;

	private List<PurchaseItem> items;

}