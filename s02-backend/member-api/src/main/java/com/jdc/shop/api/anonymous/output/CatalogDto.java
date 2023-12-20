package com.jdc.shop.api.anonymous.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.shop.model.constants.Purity;
import com.jdc.shop.model.entity.Catalog;
import com.jdc.shop.model.entity.Catalog_;
import com.jdc.shop.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogDto {

	private int id;
	private String name;
	private int categoryId;
	private String categoryName;
	private String description;
	private Purity purity;
	private BigDecimal weight;
	private BigDecimal lostWeight;
	private LocalDateTime marketTime;
	private BigDecimal marketPrice;
	private BigDecimal salePrice;
	private BigDecimal netPrice;
	private BigDecimal lostWeightFee;
	private BigDecimal goldSmithFees;
	private String coverImage;
	private LocalDateTime createAt;
	public boolean soldOut;
	
	public static void select(CriteriaQuery<CatalogDto> cq, Root<Catalog> root) {
		var catagory = root.join(Catalog_.category);
		
		cq.multiselect(
			root.get(Catalog_.id),
			root.get(Catalog_.name),
			catagory.get(Category_.id),
			catagory.get(Category_.name),
			root.get(Catalog_.description),
			root.get(Catalog_.purity),
			root.get(Catalog_.weight),
			root.get(Catalog_.lostWeight),
			root.get(Catalog_.marketTime),
			root.get(Catalog_.marketPrice),
			root.get(Catalog_.salePrice),
			root.get(Catalog_.netPrice),
			root.get(Catalog_.lostWeightFee),
			root.get(Catalog_.goldSmithFees),
			root.get(Catalog_.coverImage),
			root.get(Catalog_.createAt),
			root.get(Catalog_.soldOut)
		);
	}
	
	public static CatalogDto from(Catalog entity) {
		var dto = new CatalogDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCategoryId(entity.getCategory().getId());
		dto.setCategoryName(entity.getCategory().getName());
		dto.setDescription(entity.getDescription());
		dto.setPurity(entity.getPurity());
		dto.setWeight(entity.getWeight());
		dto.setLostWeight(entity.getLostWeight());
		dto.setMarketTime(entity.getMarketTime());
		dto.setMarketPrice(entity.getMarketPrice());
		dto.setSalePrice(entity.getSalePrice());
		dto.setNetPrice(entity.getNetPrice());
		dto.setLostWeightFee(entity.getLostWeightFee());
		dto.setGoldSmithFees(entity.getGoldSmithFees());
		dto.setCoverImage(entity.getCoverImage());
		dto.setCreateAt(entity.getCreateAt());
		dto.setSoldOut(entity.isSoldOut());
		return dto;
	}
	
	
	public GoldWeight getWeightForDisplay() {
		return new GoldWeight(weight.intValue());
	}

}