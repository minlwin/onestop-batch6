package com.jdc.shop.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jdc.shop.model.constants.Purity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "CATALOG")
@SequenceGenerator(name = "CATALOG_SEQ", allocationSize = 1)
public class Catalog extends AbstractEntity {

	@Id
	@GeneratedValue(generator = "CATALOG_SEQ")
	private int id;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;

	private String description;

	@Column(nullable = false)
	private BigDecimal basedPrice = BigDecimal.ZERO;
	
	private LocalDateTime marketTime;
	private BigDecimal marketPrice = BigDecimal.ZERO;

	private BigDecimal netPrice = BigDecimal.ZERO;
	private BigDecimal lostWeightFee = BigDecimal.ZERO;

	private BigDecimal salePrice = BigDecimal.ZERO;

	@Column(nullable = false)
	private Purity purity;

	@Column(nullable = false)
	private BigDecimal weight;

	@Column(nullable = false)
	private BigDecimal lostWeight;

	@Column(nullable = false)
	private BigDecimal goldSmithFees;

	private boolean soldOut;

	private String coverImage;
	
	@ElementCollection
	@CollectionTable(name = "CATALOG_IMAGES")
	private List<String> images = new ArrayList<String>();

}