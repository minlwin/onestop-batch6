package com.jdc.shop.api.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.api.employee.output.DistrictDto;
import com.jdc.shop.api.employee.output.StateDto;
import com.jdc.shop.api.employee.output.TownshipDto;
import com.jdc.shop.model.service.LocationService;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("employee/location")
public class LocationApi {
	
	@Autowired
	private LocationService service;

	@PostMapping("upload")
	public ApiResponse<DataModificationResult<Integer>> upload(@RequestParam MultipartFile file) {
		return ApiResponse.success(service.upload(file));
	}

	@GetMapping("state")
	public ApiResponse<List<StateDto>> searchStates() {
		return ApiResponse.success(service.getAllStates());
	}

	@GetMapping("district")
	public ApiResponse<List<DistrictDto>> searchDistrict(
			@RequestParam Optional<Integer> state, 
			@RequestParam Optional<String> name) {
		return ApiResponse.success(service.searchDistrict(state, name));
	}

	@GetMapping("township")
	public ApiResponse<List<TownshipDto>> searchTownship(
			@RequestParam Optional<Integer> district, 
			@RequestParam Optional<String> name) {
		return ApiResponse.success(service.searchTownship(district, name));
	}

}