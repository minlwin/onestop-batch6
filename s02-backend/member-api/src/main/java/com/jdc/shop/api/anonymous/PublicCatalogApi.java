package com.jdc.shop.api.anonymous;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.shop.api.anonymous.input.CatalogSearchForm;
import com.jdc.shop.api.anonymous.output.CatalogDetailsDto;
import com.jdc.shop.api.anonymous.output.CatalogDto;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.IdAndName;

@RestController
@RequestMapping("public/catalog")
public class PublicCatalogApi {

	@GetMapping
	public ApiResponse<Page<CatalogDto>> search(CatalogSearchForm form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		// TODO implement here
		return null;
	}

	@GetMapping("{id}")
	public ApiResponse<CatalogDetailsDto> showDetails(@PathVariable long id) {
		// TODO implement here
		return null;
	}

	@GetMapping("categories")
	public ApiResponse<List<IdAndName<Integer>>> findAllCategories() {
		// TODO implement here
		return null;
	}

}