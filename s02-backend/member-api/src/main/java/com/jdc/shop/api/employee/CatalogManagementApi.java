package com.jdc.shop.api.employee;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import com.jdc.shop.api.anonymous.output.CatalogDto;
import com.jdc.shop.api.employee.input.CatalogForm;
import com.jdc.shop.api.employee.input.CatalogSearch;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

public class CatalogManagementApi {

	public ApiResponse<Page<CatalogDto>> search(CatalogSearch form, int page, int size) {
		// TODO implement here
		return null;
	}

	public ApiResponse<DataModificationResult<Integer>> create(CatalogForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

	public ApiResponse<DataModificationResult<Integer>> update(int id, CatalogForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

}