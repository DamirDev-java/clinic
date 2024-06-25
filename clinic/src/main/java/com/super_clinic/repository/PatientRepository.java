package com.super_clinic.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.super_clinic.entity.Patient;
import com.super_clinic.entity.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PatientRepository extends BaseRepository<Long, Patient>{

	private final EntityManager session;

	@Autowired
	public PatientRepository(EntityManager session) {
	    super(session, Patient.class);
	    this.session = session;
	}


}
