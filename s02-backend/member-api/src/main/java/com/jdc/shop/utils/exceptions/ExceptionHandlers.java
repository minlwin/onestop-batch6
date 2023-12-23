package com.jdc.shop.utils.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.shop.utils.io.ApiResponse;

@RestControllerAdvice
public class ExceptionHandlers {
	
	private static final Map<Class<? extends AuthenticationException>, String> MESSAGES = new HashMap<>(); 
	
	static {
		MESSAGES.put(UsernameNotFoundException.class, "Please check your login id.");
		MESSAGES.put(BadCredentialsException.class, "Please check your password.");
		MESSAGES.put(AccountExpiredException.class, "Your account has been expired.");
		MESSAGES.put(DisabledException.class, "Your account is unagle to access.");
		MESSAGES.put(LockedException.class, "Your account has been locked.");
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiResponse<List<String>> handle(ApiValidationException e) {
		return ApiResponse.validationError(e.getMessages());
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiResponse<List<String>> handle(ApiBusinessException e) {
		return ApiResponse.businessError(e.getMessages());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiResponse<String> handle(MethodArgumentNotValidException e) {
		return ApiResponse.platformError(e.getMessage());
	}
	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ApiResponse<String> handle(AccessDeniedException e) {
		return ApiResponse.securityError("You have no permission to do this operation.");
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ApiResponse<String> handle(AuthenticationException e) {
		return ApiResponse.securityError(Optional.ofNullable(MESSAGES.get(e.getClass()))
				.orElse(e.getMessage()));
	}
}
