package com.super_clinic.repository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.entity.DoctorService;

import jakarta.persistence.EntityManager;

@Repository
public class DoctorServiceRepository extends BaseRepository<Long, DoctorService>{

	@Autowired
	public DoctorServiceRepository(EntityManager session) {
		super(session, DoctorService.class);
	}

}
