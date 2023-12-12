package com.jdc.shop.api.owner;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import com.jdc.shop.api.owner.input.EmployeeForm;
import com.jdc.shop.api.owner.input.EmployeeSearch;
import com.jdc.shop.api.owner.output.EmployeeDto;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

public class EmployeeManagementApi {

	public ApiResponse<Page<EmployeeDto>> search(EmployeeSearch form, int page, int size) {
		// TODO implement here
		return null;
	}

	public ApiResponse<EmployeeDto> findById(int id) {
		// TODO implement here
		return null;
	}

	public ApiResponse<DataModificationResult<Integer>> create(EmployeeForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

	public ApiResponse<DataModificationResult<Integer>> update(int id, EmployeeForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

}