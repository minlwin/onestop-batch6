package com.jdc.shop.api.owner.input;

import java.time.LocalDate;

import lombok.Data;

@Data
public class GoldPriceSearch {

	private LocalDate from;

	private LocalDate to;

}