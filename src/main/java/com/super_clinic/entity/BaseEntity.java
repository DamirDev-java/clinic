package com.super_clinic.entity;

import java.io.Serializable;


import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public interface BaseEntity<K extends Serializable> {
	
	public void setId(K id);
	
	public K getId();
}
