package com.super_clinic.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.entity.Appointment;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentRepository extends BaseRepository<Long, Appointment>{

	@Autowired
	public AppointmentRepository(EntityManager session) {
		super(session, Appointment.class);
	}

}
