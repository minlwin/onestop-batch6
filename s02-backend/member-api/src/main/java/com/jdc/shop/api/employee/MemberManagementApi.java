package com.jdc.shop.api.employee;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import com.jdc.shop.api.employee.input.MemberForm;
import com.jdc.shop.api.employee.input.MemberSearch;
import com.jdc.shop.api.employee.output.MemberDto;
import com.jdc.shop.api.member.output.MemberProfile;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

public class MemberManagementApi {

	public ApiResponse<Page<MemberProfile>> search(MemberSearch form, int page, int size) {
		// TODO implement here
		return null;
	}

	public ApiResponse<MemberDto> findById(int id) {
		// TODO implement here
		return null;
	}

	public ApiResponse<DataModificationResult<Integer>> create(MemberForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

	public ApiResponse<DataModificationResult<Integer>> update(int id, MemberForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

}