package com.jdc.shop.api.employee;

import java.util.List;

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
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("employee/category")
public class CatagoryManagementApi {

	@GetMapping
	public ApiResponse<List<CatagoryDto>> search() {
		// TODO implement here
		return null;
	}

	
	@GetMapping("{id}")
	public ApiResponse<CatagoryDto> findById(@PathVariable int id) {
		// TODO implement here
		return null;
	}

	@PostMapping
	public ApiResponse<DataModificationResult<Integer>> create(
			@Validated @RequestBody CategoryForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

	@PutMapping("{id}")
	public ApiResponse<DataModificationResult<Integer>> update(@PathVariable int id, 
			@Validated @RequestBody CategoryForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

}