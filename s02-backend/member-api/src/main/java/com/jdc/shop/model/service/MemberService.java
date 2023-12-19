package com.jdc.shop.model.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jdc.shop.api.employee.input.MemberForm;
import com.jdc.shop.api.employee.input.MemberSearch;
import com.jdc.shop.api.employee.output.MemberDto;
import com.jdc.shop.api.member.output.MemberProfile;
import com.jdc.shop.utils.io.DataModificationResult;

@Service
public class MemberService {

	public Page<MemberProfile> search(MemberSearch form, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public MemberDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<Integer> create(MemberForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<Integer> update(int id, MemberForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
