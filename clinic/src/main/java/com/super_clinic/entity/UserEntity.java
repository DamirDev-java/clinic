package com.super_clinic.entity;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class UserEntity<K extends Serializable> implements BaseEntity<K> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private K id;
	
	private String username;
	
	private String password;
}
