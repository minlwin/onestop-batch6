package com.jdc.shop.model;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>{
	
	private EntityManager entityManager;

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Long count(Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc) {
		var query = countFunc.apply(entityManager.getCriteriaBuilder());
		return entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		var query = queryFunc.apply(entityManager.getCriteriaBuilder());
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc, int limit) {
		var cq = queryFunc.apply(entityManager.getCriteriaBuilder());
		return entityManager.createQuery(cq).setMaxResults(limit).getResultList();
	}

	@Override
	public <R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {
		var count = count(countFunc);
		var query = entityManager.createQuery(queryFunc.apply(entityManager.getCriteriaBuilder()));
		query.setFirstResult(size * page);
		query.setMaxResults(size);
		var list = query.getResultList();
		
		return new PageImpl<R>(list, PageRequest.of(page, size), count);
	}

}
