package com.jdc.shop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.api.anonymous.input.LoginForm;
import com.jdc.shop.api.anonymous.output.LoginResult;
import com.jdc.shop.model.repo.AccountRepo;

@Service
public class LoginService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AccountRepo accountRepo;

	@Transactional(readOnly = true)
	public LoginResult login(LoginForm form) {
		
		var authentication = authenticationManager.authenticate(form.authentication());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return accountRepo.findById(form.getUsername()).map(LoginResult::from).get();
	}

}
