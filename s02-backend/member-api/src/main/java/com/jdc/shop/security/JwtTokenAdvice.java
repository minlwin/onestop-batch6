package com.jdc.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class JwtTokenAdvice implements ResponseBodyAdvice<Object>{
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		return null != authentication && authentication.isAuthenticated();
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		tokenProvider.generateToken(authentication)
			.filter(StringUtils::hasLength)
			.ifPresent(token -> {
			
			if(response instanceof ServletServerHttpResponse servletResponse) {
				servletResponse.getHeaders().add("Authorization", token);
			}
		});
		
		return body;
	}

}
