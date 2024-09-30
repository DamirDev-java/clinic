package com.super_clinic.repository;

import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.entity.Appointment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends BaseRepository<Appointment, Long> {
}
