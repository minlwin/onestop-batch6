package com.jdc.shop.api.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.shop.api.member.input.PurchaseSearch;
import com.jdc.shop.api.member.output.PurchaseDetailsDto;
import com.jdc.shop.api.member.output.PurchaseDto;
import com.jdc.shop.model.service.PurchaseService;
import com.jdc.shop.utils.io.ApiResponse;

@RestController
@RequestMapping("member/purchase")
public class MemberPurchaseApi {
	
	@Autowired
	private PurchaseService service;

	@GetMapping
	public ApiResponse<Page<PurchaseDto>> search(PurchaseSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(form, page, size));
	}

	@GetMapping("{id}")
	public ApiResponse<PurchaseDetailsDto> showDetails(@PathVariable String id) {
		return ApiResponse.success(service.findById(id));
	}

}