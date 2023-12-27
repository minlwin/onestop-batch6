package com.jdc.shop.utils;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JpaAuditorAwareBean implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
				.map(a -> a.getName());
	}

}
