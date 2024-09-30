package com.super_clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import com.super_clinic.entity.Patient;

import jakarta.persistence.EntityManager;

@Repository
public interface PatientRepository extends BaseRepository<Patient, Long>, BaseUserRepository<Patient, Long> {

}
