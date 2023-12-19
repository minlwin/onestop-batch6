package com.jdc.shop.api.employee;

import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.api.anonymous.output.CatalogDto;
import com.jdc.shop.api.employee.input.CatalogForm;
import com.jdc.shop.api.employee.input.CatalogSearch;
import com.jdc.shop.model.service.CatalogService;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("employee/catalog")
public class CatalogManagementApi {
	
	@Autowired
	private CatalogService service;

	@GetMapping
	public ApiResponse<Page<CatalogDto>> search(CatalogSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(form, page, size));
	}

	@PostMapping
	public ApiResponse<DataModificationResult<Integer>> create(
			@Validated @RequestBody CatalogForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}

	@PutMapping("{id}")
	public ApiResponse<DataModificationResult<Integer>> update(
			@PathVariable int id, 
			@Validated @RequestBody CatalogForm form, BindingResult result) {
		return ApiResponse.success(service.update(id, form));
	}

	@PutMapping("{id}/photos")
	public ApiResponse<DataModificationResult<Integer>> uploadPhoto(
			@PathVariable int id, 
			@RequestParam List<MultipartFile> files) {
		return ApiResponse.success(service.uploadPhoto(id, files));
	}
}