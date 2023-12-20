package com.jdc.shop.api.employee;

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

import com.jdc.shop.api.employee.input.SaleForm;
import com.jdc.shop.api.employee.input.SaleSearch;
import com.jdc.shop.api.employee.output.SaleDetailsDto;
import com.jdc.shop.api.employee.output.SaleDto;
import com.jdc.shop.model.service.SaleService;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("employee/sales")
public class SaleManagementApi {
	
	@Autowired
	private SaleService service;
	
	@GetMapping
	public ApiResponse<Page<SaleDto>> search(SaleSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(form, page, size));
	}

	@GetMapping("{id}")
	public ApiResponse<SaleDetailsDto> findById(@PathVariable String id) {
		return ApiResponse.success(service.findById(id));
	}

	@PostMapping
	public ApiResponse<DataModificationResult<String>> create(
			@RequestBody @Validated SaleForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}

	@PutMapping("{id}")
	public ApiResponse<DataModificationResult<String>> update(
			@PathVariable String id, 
			@RequestBody @Validated SaleForm form, BindingResult result) {
		return ApiResponse.success(service.update(id, form));
	}

}