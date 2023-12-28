package com.jdc.shop.api.employee;

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

import com.jdc.shop.api.employee.input.MemberForm;
import com.jdc.shop.api.employee.input.MemberSearch;
import com.jdc.shop.api.employee.output.MemberDto;
import com.jdc.shop.api.member.output.MemberProfile;
import com.jdc.shop.model.service.MemberService;
import com.jdc.shop.utils.io.ApiResponse;
import com.jdc.shop.utils.io.DataModificationResult;

@RestController
@RequestMapping("employee/member")
public class MemberManagementApi {
	
	@Autowired
	private MemberService service;

	@GetMapping
	public ApiResponse<Page<MemberProfile>> search(MemberSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return ApiResponse.success(service.search(form, page, size));
	}

	@GetMapping("{id}")
	public ApiResponse<MemberDto> findById(@PathVariable int id) {
		return ApiResponse.success(service.findById(id));
	}

	@PostMapping
	public ApiResponse<DataModificationResult<Integer>> create(
			@Validated @RequestBody MemberForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}

	@PutMapping("{id}")
	public ApiResponse<DataModificationResult<Integer>> update(@PathVariable int id, 
			@Validated @RequestBody MemberForm form, BindingResult result) {
		return ApiResponse.success(service.update(id, form));
	}

	@PutMapping("{id}/photo")
	public ApiResponse<DataModificationResult<Integer>> uploadPhoto(@PathVariable int id, 
			@RequestParam MultipartFile file) {
		return ApiResponse.success(service.uploadPhoto(id, file));
	}
}