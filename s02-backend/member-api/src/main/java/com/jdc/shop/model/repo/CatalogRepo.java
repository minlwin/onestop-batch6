package com.jdc.shop.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.shop.model.BaseRepository;
import com.jdc.shop.model.entity.Catalog;

public interface CatalogRepo extends BaseRepository<Catalog, Integer>{

	@Query("select t.id from Catalog t where t.deleted = false and t.soldOut = false")
	List<Integer> searchIdForPriceChange();
}
