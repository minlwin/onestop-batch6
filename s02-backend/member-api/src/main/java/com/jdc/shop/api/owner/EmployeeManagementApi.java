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

import com.jdc.shop.api.owner.input.EmployeeForm;
import com.jdc.shop.api.owner.input.EmployeeSearch;
import com.jdc.shop.api.owner.output.EmployeeDto;
import com.jdc.shop.model.service.EmployeeService;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("owner/employee")
public class EmployeeManagementApi {
	
	@Autowired
	private EmployeeService service;

	@GetMapping
	public ApiResponse<Page<EmployeeDto>> search(EmployeeSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(form, page, size));
	}

	@GetMapping("{id}")
	public ApiResponse<EmployeeDto> findById(@PathVariable int id) {
		return ApiResponse.success(service.findById(id));
	}

	@PostMapping
	public ApiResponse<DataModificationResult<Integer>> create(
			@RequestBody @Validated EmployeeForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}

	@PutMapping("{id}")
	public ApiResponse<DataModificationResult<Integer>> update(@PathVariable int id, 
			@RequestBody @Validated EmployeeForm form, BindingResult result) {
		return ApiResponse.success(service.update(id, form));
	}

}