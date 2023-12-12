package com.jdc.shop.api.employee;

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
import com.jdc.shop.model.entity.pk.SalePk;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("employee/sales")
public class SaleManagementApi {
	
	@GetMapping
	public ApiResponse<Page<SaleDto>> search(SaleSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		// TODO implement here
		return null;
	}

	@GetMapping("{id}")
	public ApiResponse<SaleDetailsDto> findById(@PathVariable String id) {
		// TODO implement here
		return null;
	}

	@PostMapping
	public ApiResponse<DataModificationResult<SalePk>> create(
			@RequestBody @Validated SaleForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

	@PutMapping("{id}")
	public ApiResponse<DataModificationResult<SalePk>> update(
			@PathVariable String id, 
			@RequestBody @Validated SaleForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

}