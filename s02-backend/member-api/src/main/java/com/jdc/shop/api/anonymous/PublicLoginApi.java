package com.jdc.shop.api.anonymous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.shop.api.anonymous.input.LoginForm;
import com.jdc.shop.api.anonymous.output.LoginResult;
import com.jdc.shop.model.service.LoginService;
import com.jdc.shop.utils.exceptions.ApiValidationException;
import com.jdc.shop.utils.io.ApiResponse;

@RestController
@RequestMapping("public/login")
public class PublicLoginApi {
	
	@Autowired
	private LoginService service;

	@PostMapping
	public ApiResponse<LoginResult> login(
			@Validated @RequestBody LoginForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			throw new ApiValidationException(result.getFieldErrors()
					.stream().map(a -> a.getDefaultMessage()).toList());
		}
		
		return ApiResponse.success(service.login(form));
	}

}