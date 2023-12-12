package com.jdc.shop.api.owner;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import com.jdc.shop.api.owner.input.GlodPriceForm;
import com.jdc.shop.api.owner.input.GoldPriceSearch;
import com.jdc.shop.api.owner.output.GlodPriceDto;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

public class GoldPriceManagementApi {

	public ApiResponse<Page<GlodPriceDto>> search(GoldPriceSearch form, int page, int size) {
		// TODO implement here
		return null;
	}

	public ApiResponse<GlodPriceDto> findById(long id) {
		// TODO implement here
		return null;
	}

	public ApiResponse<DataModificationResult<LocalDateTime>> create(GlodPriceForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

	public ApiResponse<DataModificationResult<LocalDateTime>> update(long id, GlodPriceForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

}