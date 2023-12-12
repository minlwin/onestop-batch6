package com.jdc.shop.api.employee.input;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.model.constants.Purity;

import lombok.Data;

@Data
public class CatalogForm {

	private String name;

	private int categoryId;

	private String description;

	private BigDecimal basedPrice;

	private List<MultipartFile> images;

	private Purity purity;

	private BigDecimal weight;

	private BigDecimal lostWeight;

	private BigDecimal goldSmithFees;

}