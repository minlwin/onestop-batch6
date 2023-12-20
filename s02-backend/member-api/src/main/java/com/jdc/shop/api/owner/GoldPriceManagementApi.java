package com.jdc.shop.api.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.shop.api.owner.input.GlodPriceForm;
import com.jdc.shop.api.owner.input.GoldPriceSearch;
import com.jdc.shop.api.owner.output.GoldPriceDto;
import com.jdc.shop.model.service.GoldPriceService;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("owner/price")
public class GoldPriceManagementApi {
	
	@Autowired
	private GoldPriceService service;

	@GetMapping
	public ApiResponse<Page<GoldPriceDto>> search(
			GoldPriceSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(form, page, size));
	}

	@GetMapping("{id}")
	public ApiResponse<GoldPriceDto> findById(@PathVariable long id) {
		return ApiResponse.success(service.findById(id));
	}

	@PostMapping
	public ApiResponse<DataModificationResult<Long>> create(
			@RequestBody @Validated GlodPriceForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}

	@PutMapping("{id}")
	public ApiResponse<DataModificationResult<Long>> update(
			@PathVariable long id, 
			@RequestBody @Validated GlodPriceForm form, BindingResult result) {
		return ApiResponse.success(service.update(id, form));
	}

}