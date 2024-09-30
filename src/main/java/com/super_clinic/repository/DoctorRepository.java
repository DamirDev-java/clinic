 package com.super_clinic.repository;

import org.springframework.stereotype.Repository;

import com.super_clinic.entity.Doctor;

@Repository
public interface DoctorRepository extends BaseRepository<Doctor, Long>, BaseUserRepository<Doctor, Long> {

}
