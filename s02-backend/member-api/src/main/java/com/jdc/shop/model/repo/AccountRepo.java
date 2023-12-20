package com.jdc.shop.model.repo;

import com.jdc.shop.model.BaseRepository;
import com.jdc.shop.model.entity.Account;

public interface AccountRepo extends BaseRepository<Account, String>{

	long countByLoginId(String loginId);

}
