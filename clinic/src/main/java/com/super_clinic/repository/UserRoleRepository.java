package com.super_clinic.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.entity.UserRole;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class UserRoleRepository extends BaseRepository<Long, UserRole>{
	
	private PatientRepository patientRepository;
	
	private DoctorRepository doctorRepository;

	private EntityManager session;
	
	@Autowired
	public UserRoleRepository(EntityManager session, DoctorRepository doctorRepository, PatientRepository patientRepository) {
		super(session, UserRole.class);
		this.session = session;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
	}
	
	
} 
