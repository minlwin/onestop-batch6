package com.jdc.shop.api.employee;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import com.jdc.shop.api.employee.input.SaleForm;
import com.jdc.shop.api.employee.input.SaleSearch;
import com.jdc.shop.api.employee.output.SaleDetailsDto;
import com.jdc.shop.api.employee.output.SaleDto;
import com.jdc.shop.model.entity.pk.SalePk;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

public class SaleManagementApi {

	public ApiResponse<Page<SaleDto>> search(SaleSearch form, int page, int size) {
		// TODO implement here
		return null;
	}

	public ApiResponse<SaleDetailsDto> findById(String id) {
		// TODO implement here
		return null;
	}

	public ApiResponse<DataModificationResult<SalePk>> create(SaleForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

	public ApiResponse<DataModificationResult<SalePk>> update(String id, SaleForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

}