package com.jdc.shop.model;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepositoryImplementation<T, ID>{

	Long count(Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc);
	<R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc);
	<R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc, int limit);
	<R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc, 
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size);
}
