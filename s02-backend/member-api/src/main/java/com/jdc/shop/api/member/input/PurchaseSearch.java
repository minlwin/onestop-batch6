package com.jdc.shop.api.member.input;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PurchaseSearch {

	private LocalDate dateFrom;

	private LocalDate dateTo;

}