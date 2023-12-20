package com.jdc.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jdc.shop.model.constants.Role;
import com.jdc.shop.security.JwtTokenFilter;
import com.jdc.shop.utils.exceptions.ApiSecurityExceptionResolver;

@Configuration
@EnableMethodSecurity
public class MemberApiSecurityConfiguration {
	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	@Autowired
	private ApiSecurityExceptionResolver apiSecurityExceptionResolver;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> {});
		
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("/resources/**", "/public/**", "/api/v1/auth/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll();
			req.requestMatchers("/member/**").hasAnyAuthority(Role.Member.name(), Role.Admin.name());
			req.requestMatchers("/employee/**").hasAnyAuthority(Role.Employee.name(), Role.Owner.name(), Role.Admin.name());
			req.requestMatchers("/owner/**").hasAnyAuthority(Role.Owner.name(), Role.Admin.name());
			req.anyRequest().denyAll();
		});
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(basic -> basic.disable());
		
		http.exceptionHandling(config -> {
			config.accessDeniedHandler(apiSecurityExceptionResolver);
			config.authenticationEntryPoint(apiSecurityExceptionResolver);
		});
		
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
