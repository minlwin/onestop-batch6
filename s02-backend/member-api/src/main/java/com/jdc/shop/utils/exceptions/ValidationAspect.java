package com.jdc.shop.utils.exceptions;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class ValidationAspect {
	
	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	public void apiClasses() {}

	@Before(value = "apiClasses() && args(.., result)", argNames = "result")
	public void check(BindingResult result) {
		if(result.hasErrors()) {
			throw new ApiValidationException(result.getFieldErrors()
					.stream().map(a -> a.getDefaultMessage()).toList());
		}
	}
}
