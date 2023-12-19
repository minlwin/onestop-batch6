package com.jdc.shop.api.employee.output;

import java.util.List;

import com.jdc.shop.api.member.output.MemberProfile;
import com.jdc.shop.api.member.output.PurchaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

	private MemberProfile profile;

	private List<PurchaseDto> purchases;

}