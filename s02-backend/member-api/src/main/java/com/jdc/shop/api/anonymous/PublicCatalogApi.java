package com.jdc.shop.api.anonymous;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.shop.api.anonymous.output.CatalogDetailsDto;
import com.jdc.shop.api.anonymous.output.CatalogDto;
import com.jdc.shop.api.employee.input.CatalogSearch;
import com.jdc.shop.api.employee.output.CatagoryDto;
import com.jdc.shop.model.service.CatalogService;
import com.jdc.shop.model.service.CategoryService;
import com.jdc.shop.utils.io.ApiResponse;

@RestController
@RequestMapping("public/catalog")
public class PublicCatalogApi {
	
	@Autowired
	private CatalogService service;
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ApiResponse<Page<CatalogDto>> search(CatalogSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(form, page, size));
	}

	@GetMapping("{id}")
	public ApiResponse<CatalogDetailsDto> showDetails(@PathVariable int id) {
		return ApiResponse.success(service.findById(id));
	}

	@GetMapping("categories")
	public ApiResponse<List<CatagoryDto>> findAllCategories() {
		return ApiResponse.success(categoryService.findAll());
	}

}