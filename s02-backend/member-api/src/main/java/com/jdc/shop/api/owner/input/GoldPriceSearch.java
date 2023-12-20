package com.jdc.shop.api.owner.input;

import java.time.LocalDate;

import com.jdc.shop.model.entity.GoldPrice.Status;

import lombok.Data;

@Data
public class GoldPriceSearch {

	private LocalDate from;
	private LocalDate to;
	private Status status;

}