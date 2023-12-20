package com.jdc.shop.api.anonymous.output;

import java.util.List;

import com.jdc.shop.model.entity.Catalog;

import lombok.Data;

@Data
public class CatalogDetailsDto {

	private CatalogDto baseInfo;
	private List<String> images;
	
	public static CatalogDetailsDto from(Catalog entity) {
		var dto = new CatalogDetailsDto();
		dto.setBaseInfo(CatalogDto.from(entity));
		dto.setImages(entity.getImages());
		return dto;
	}

}