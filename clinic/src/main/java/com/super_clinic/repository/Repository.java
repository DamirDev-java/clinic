package com.super_clinic.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

import static java.util.Collections.emptyMap;

import java.util.List;

public interface Repository<K, E> {
	
	public E save(E e);
	
    Optional<E> findById(K id, Map<String, Object> properties);
	
	default Optional<E> findById(K id) {
		return findById(id, emptyMap());
	}
	
	public void update (E e);
	
	public void delete(E e);
	
	public List<E> findAll();
	
	public Optional<E> findByUsername(String username);
	
	
	
	 E map(K id);
}
