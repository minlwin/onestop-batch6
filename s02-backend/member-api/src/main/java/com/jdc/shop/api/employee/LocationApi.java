package com.jdc.shop.api.employee;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.api.employee.output.DistrictDto;
import com.jdc.shop.api.employee.output.StateDto;
import com.jdc.shop.api.employee.output.TownshipDto;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

public class LocationApi {

	public ApiResponse<DataModificationResult<Integer>> upload(MultipartFile file) {
		// TODO implement here
		return null;
	}

	public ApiResponse<List<StateDto>> searchStates() {
		// TODO implement here
		return null;
	}

	public ApiResponse<List<DistrictDto>> searchDistrict(int state, String name) {
		// TODO implement here
		return null;
	}

	public ApiResponse<List<TownshipDto>> searchTownship(int district, String name) {
		// TODO implement here
		return null;
	}

}