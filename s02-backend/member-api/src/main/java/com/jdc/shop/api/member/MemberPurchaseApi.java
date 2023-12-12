package com.jdc.shop.api.member;

import org.springframework.data.domain.Page;

import com.jdc.shop.api.member.input.PurchaseSearch;
import com.jdc.shop.api.member.output.PurchaseDetailsDto;
import com.jdc.shop.api.member.output.PurchaseDto;
import com.jdc.shop.utils.io.ApiResponse;

public class MemberPurchaseApi {

	public ApiResponse<Page<PurchaseDto>> search(PurchaseSearch form, int page, int size) {
		// TODO implement here
		return null;
	}

	public ApiResponse<PurchaseDetailsDto> showDetails(String id) {
		// TODO implement here
		return null;
	}

}