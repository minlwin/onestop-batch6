package com.jdc.shop.api.employee.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.shop.api.member.output.MemberProfile;
import com.jdc.shop.model.entity.pk.SalePk;

import lombok.Data;

@Data
public class SaleDto {

	private SalePk id;

	private MemberProfile customer;

	private LocalDateTime saleAt;

	private BigDecimal salePrice;

	private BigDecimal discount;

}