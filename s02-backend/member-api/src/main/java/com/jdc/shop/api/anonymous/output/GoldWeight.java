package com.jdc.shop.api.anonymous.output;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GoldWeight {

	private final int netWeight;

	public int getKyat() {
		return netWeight / 128;
	}

	public int getPae() {
		var remain = netWeight % 128;
		return remain / 8;
	}

	public int getYwe() {
		var remain = netWeight % 128;
		return remain % 8;
	}

}