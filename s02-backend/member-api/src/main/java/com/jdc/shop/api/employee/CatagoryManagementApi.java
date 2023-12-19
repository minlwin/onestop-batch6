package com.jdc.shop.api.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.shop.api.employee.input.CategoryForm;
import com.jdc.shop.api.employee.output.CatagoryDto;
import com.jdc.shop.model.service.CategoryService;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("employee/category")
public class CatagoryManagementApi {
	
	@Autowired
	private CategoryService service;

	@GetMapping
	public ApiResponse<List<CatagoryDto>> search() {
		return ApiResponse.success(service.findAll());
	}

	
	@GetMapping("{id}")
	public ApiResponse<CatagoryDto> findById(@PathVariable int id) {
		return ApiResponse.success(service.findById(id));
	}

	@PostMapping
	public ApiResponse<DataModificationResult<Integer>> create(
			@Validated @RequestBody CategoryForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}

	@PutMapping("{id}")
	public ApiResponse<DataModificationResult<Integer>> update(@PathVariable int id, 
			@Validated @RequestBody CategoryForm form, BindingResult result) {
		return ApiResponse.success(service.update(id, form));
	}

}