package com.jdc.shop.api.owner;

import java.time.LocalDateTime;

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
import com.jdc.shop.api.owner.output.GlodPriceDto;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("member/price")
public class GoldPriceManagementApi {

	@GetMapping
	public ApiResponse<Page<GlodPriceDto>> search(
			GoldPriceSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		// TODO implement here
		return null;
	}

	@GetMapping("{id}")
	public ApiResponse<GlodPriceDto> findById(@PathVariable long id) {
		// TODO implement here
		return null;
	}

	@PostMapping
	public ApiResponse<DataModificationResult<LocalDateTime>> create(
			@RequestBody @Validated GlodPriceForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

	@PutMapping("{id}")
	public ApiResponse<DataModificationResult<LocalDateTime>> update(
			long id, 
			@RequestBody @Validated GlodPriceForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

}