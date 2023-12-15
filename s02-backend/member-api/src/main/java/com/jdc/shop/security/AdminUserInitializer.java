package com.jdc.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.shop.model.constants.Role;
import com.jdc.shop.model.entity.Account;
import com.jdc.shop.model.repo.AccountRepo;

@Configuration
public class AdminUserInitializer {
	
	@Autowired
	private AccountRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(classes = ContextRefreshedEvent.class)
	void initializeAdmin() {
		if(repo.count() == 0L) {
			var admin = new Account();
			admin.setName("Admin User");
			admin.setLoginId("admin");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setRole(Role.Admin);
			repo.save(admin);
		}
	}
}
