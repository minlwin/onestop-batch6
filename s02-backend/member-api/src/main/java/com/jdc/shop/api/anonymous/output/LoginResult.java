package com.jdc.shop.api.anonymous.output;

import com.jdc.shop.model.constants.Role;
import com.jdc.shop.model.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {

	private String loginId;
	private String name;
	private Role role;
	
	public static LoginResult from(Account account) {
		return new LoginResult(account.getLoginId(), account.getName(), account.getRole());
	}
}