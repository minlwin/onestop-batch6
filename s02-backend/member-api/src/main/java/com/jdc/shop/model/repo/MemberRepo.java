package com.jdc.shop.model.repo;

import java.util.Optional;

import com.jdc.shop.model.BaseRepository;
import com.jdc.shop.model.entity.Member;

public interface MemberRepo extends BaseRepository<Member, Integer>{

	Optional<Member> findOneByAccountLoginId(String loginId);
}
