package com.super_clinic.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.entity.Service;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

@Repository
public class ServiceRepository extends BaseRepository<Long, Service>{

	@Autowired
	public ServiceRepository(EntityManager session) {
		super(session, Service.class);
	}

}
