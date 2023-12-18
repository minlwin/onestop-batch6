package com.jdc.shop.api.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.api.employee.output.DistrictDto;
import com.jdc.shop.api.employee.output.StateDto;
import com.jdc.shop.api.employee.output.TownshipDto;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("employee/location")
public class LocationApi {

	@PostMapping("upload")
	public ApiResponse<DataModificationResult<Integer>> upload(@RequestParam MultipartFile file) {
		// TODO implement here
		return null;
	}

	@GetMapping("state")
	public ApiResponse<List<StateDto>> searchStates() {
		return ApiResponse.success(List.of());
	}

	@GetMapping("district")
	public ApiResponse<List<DistrictDto>> searchDistrict(
			@RequestParam Optional<Integer> state, 
			@RequestParam Optional<String> name) {
		// TODO implement here
		return null;
	}

	@GetMapping("township")
	public ApiResponse<List<TownshipDto>> searchTownship(
			@RequestParam Optional<Integer> district, 
			@RequestParam Optional<String> name) {
		// TODO implement here
		return null;
	}

}