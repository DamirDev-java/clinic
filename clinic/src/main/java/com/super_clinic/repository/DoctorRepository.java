 package com.super_clinic.repository;

import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.super_clinic.entity.Doctor;
import com.super_clinic.entity.Patient;
import com.super_clinic.entity.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class DoctorRepository extends BaseRepository<Long, Doctor> {

	private final EntityManager session;
	
	@Autowired
	public DoctorRepository(EntityManager session) {
		super(session, Doctor.class);
		this.session = session;
	}
	
	 public Optional<Doctor> findByName(String name) {
		 Query query = session.createQuery("SELECT e FROM " + Doctor.class + " e WHERE e.name = :name");
		 query.setParameter("name", name);
		 Doctor e = (Doctor) query.getSingleResult();
	     return Optional.ofNullable(e); 
	 }
	
}
