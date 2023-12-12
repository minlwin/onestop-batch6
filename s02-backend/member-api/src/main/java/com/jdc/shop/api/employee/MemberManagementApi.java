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

import com.jdc.shop.api.employee.input.MemberForm;
import com.jdc.shop.api.employee.input.MemberSearch;
import com.jdc.shop.api.employee.output.MemberDto;
import com.jdc.shop.api.member.output.MemberProfile;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("employee/member")
public class MemberManagementApi {

	@GetMapping
	public ApiResponse<Page<MemberProfile>> search(MemberSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		// TODO implement here
		return null;
	}

	@GetMapping("{id}")
	public ApiResponse<MemberDto> findById(@PathVariable int id) {
		// TODO implement here
		return null;
	}

	@PostMapping
	public ApiResponse<DataModificationResult<Integer>> create(
			@Validated @RequestBody MemberForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

	@PutMapping("{id}")
	public ApiResponse<DataModificationResult<Integer>> update(int id, 
			@Validated @RequestBody MemberForm form, BindingResult result) {
		// TODO implement here
		return null;
	}

}