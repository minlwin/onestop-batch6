package com.jdc.shop.api.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.shop.api.member.output.MemberProfile;
import com.jdc.shop.utils.io.ApiResponse;

@RestController
@RequestMapping("member/profile")
public class MemberProfileApi {

	@GetMapping
	public ApiResponse<MemberProfile> getProfile() {
		// TODO implement here
		return null;
	}

}