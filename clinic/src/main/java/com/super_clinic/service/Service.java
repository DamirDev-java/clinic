package com.super_clinic.service;

import java.util.List;
import java.util.Optional;

public interface Service<K, D> {

    public Optional<D> findById(K id);
	
	public void delete(D d);
	
	public void update(D d);

    List<D> findAll();
    
    public Optional<D> findByUsername(String username);
}
