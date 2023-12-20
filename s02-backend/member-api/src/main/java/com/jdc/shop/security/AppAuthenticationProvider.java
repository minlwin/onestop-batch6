package com.jdc.shop.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppAuthenticationProvider extends DaoAuthenticationProvider{

	public AppAuthenticationProvider(PasswordEncoder passwordEncoder, AppUserDetailsService appUserDetailsService) {
		super(passwordEncoder);
		setUserDetailsService(appUserDetailsService);
		setHideUserNotFoundExceptions(false);
	}
}
