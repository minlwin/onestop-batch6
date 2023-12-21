package com.jdc.shop.security;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.shop.model.constants.Role;
import com.jdc.shop.model.repo.AccountRepo;
import com.jdc.shop.model.repo.EmployeeRepo;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		var account = accountRepo.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		
		var builder = User.withUsername(username)
				.password(account.getPassword())
				.authorities(account.getRole().name());
		
		if(account.getRole() == Role.Employee) {
			var employee = employeeRepo.findOneByAccountLoginId(username)
					.orElseThrow(() -> new UsernameNotFoundException(username));
			
			if(null != employee.getAssignAt()) {
				builder.disabled(LocalDate.now().compareTo(employee.getAssignAt()) < 0);
			}
			
			if(null != employee.getRetiredAt()) {
				builder.accountExpired(LocalDate.now().compareTo(employee.getRetiredAt()) >= 0);
			}			
		}
		
		return builder.build();
	}

}
