package com.jdc.shop.api.member.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.shop.model.constants.Gender;
import com.jdc.shop.model.entity.Address_;
import com.jdc.shop.model.entity.District_;
import com.jdc.shop.model.entity.Member;
import com.jdc.shop.model.entity.Member_;
import com.jdc.shop.model.entity.State_;
import com.jdc.shop.model.entity.Township_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberProfile {

	private int id;
	private String name;
	private String profileImage;
	private String phone;
	private String email;
	private LocalDate dob;
	private Gender gender;
	private String address;
	private String township;
	private String state;
	private LocalDateTime registrationDate;
	
	public static MemberProfile from(Member entity) {
		var dto = new MemberProfile();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setProfileImage(entity.getProfileImage());
		dto.setPhone(entity.getPhone());
		dto.setEmail(entity.getEmail());
		dto.setDob(entity.getDob());
		dto.setGender(entity.getGender());
		dto.setAddress(entity.getAddress().getAddress());
		dto.setTownship(entity.getAddress().getTownship().getName());
		dto.setState(entity.getAddress().getTownship().getDistrict().getState().getName());
		dto.setRegistrationDate(entity.getRegistAt());
		return dto;
	}
	
	public static void select(CriteriaQuery<MemberProfile> cq, Root<Member> root) {
		
		var address = root.join(Member_.address, JoinType.LEFT);
		
		cq.multiselect(
			root.get(Member_.id),
			root.get(Member_.name),
			root.get(Member_.profileImage),
			root.get(Member_.phone),
			root.get(Member_.email),
			root.get(Member_.dob),
			root.get(Member_.gender),
			address.get(Address_.address),
			address.get(Address_.township).get(Township_.name),
			address.get(Address_.township).get(Township_.district).get(District_.state).get(State_.name),
			root.get(Member_.registAt)			
		);
	}

}