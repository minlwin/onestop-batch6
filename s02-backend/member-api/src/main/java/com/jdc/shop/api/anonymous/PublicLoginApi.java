package com.jdc.shop.api.anonymous;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.shop.api.anonymous.input.LoginForm;
import com.jdc.shop.api.anonymous.output.LoginResult;
import com.jdc.shop.utils.io.ApiResponse;

@RestController
@RequestMapping("public/login")
public class PublicLoginApi {

	@PostMapping
	public ApiResponse<LoginResult> login(@RequestBody LoginForm form, BindingResult result) {
		return null;
	}

}