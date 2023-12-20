package com.jdc.shop.api.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.shop.api.member.output.MemberProfile;
import com.jdc.shop.model.service.MemberService;
import com.jdc.shop.utils.io.ApiResponse;

@RestController
@RequestMapping("member/profile")
public class MemberProfileApi {
	
	@Autowired
	private MemberService service;

	@GetMapping
	public ApiResponse<MemberProfile> getProfile() {
		
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return ApiResponse.success(service.findByUserName(username));
	}

}