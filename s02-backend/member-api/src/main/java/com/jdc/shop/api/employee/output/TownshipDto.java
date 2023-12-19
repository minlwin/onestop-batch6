package com.jdc.shop.api.employee.output;

import com.jdc.shop.model.entity.District_;
import com.jdc.shop.model.entity.State_;
import com.jdc.shop.model.entity.Township;
import com.jdc.shop.model.entity.Township_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TownshipDto {

	private int id;

	private String name;

	private DistrictDto district;
	
	public static void select(CriteriaQuery<TownshipDto> cq, Root<Township> root) {
		cq.multiselect(
			root.get(Township_.id),
			root.get(Township_.name),
			root.get(Township_.district).get(District_.id),
			root.get(Township_.district).get(District_.name),
			root.get(Township_.district).get(District_.state).get(State_.id),
			root.get(Township_.district).get(District_.state).get(State_.name)
		);
	}

	public TownshipDto(int id, String name, int districtId, String districtName, int stateId, String stateName) {
		super();
		this.id = id;
		this.name = name;
		this.district = new DistrictDto(districtId, districtName, stateId, stateName);
	}

}