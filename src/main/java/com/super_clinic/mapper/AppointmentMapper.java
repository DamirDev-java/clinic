package com.super_clinic.mapper;

import java.util.List;

import com.super_clinic.entity.*;
import com.super_clinic.repository.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.super_clinic.dto.AppointmentDto;
import com.super_clinic.service.impl.DoctorServiceImpl;
import com.super_clinic.service.impl.PatientServiceImpl;
import com.super_clinic.service.impl.ServiceServiceImpl;

@Mapper(componentModel = "spring", uses = {DoctorRepository.class, PatientRepository.class, ServiceRepository.class})
public abstract class AppointmentMapper implements BaseMapper<Appointment, AppointmentDto> {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	@Mapping(source = "patientId", target = "patient", qualifiedByName = "findPatientById")
	@Mapping(source = "doctorId", target = "doctor", qualifiedByName = "findDoctorById")
	@Mapping(source = "serviceId", target = "service", qualifiedByName = "findServiceById")
	public abstract Appointment dtoToEntity(AppointmentDto dto);

	@Override
	public abstract List<Appointment> dtosToEntity(List<AppointmentDto> dtos);

	@Override
	public abstract AppointmentDto entityToDto(Appointment entity);

	@Override
	public abstract List<AppointmentDto> entitiesToDtos(List<Appointment> entities);

	@Named("findPatientById")
	protected Patient findPatientById(Long patientId) {
		return patientRepository.findById(patientId).orElse(null);
	}

	@Named("findDoctorById")
	protected Doctor findDoctorById(Long doctorId) {
		return doctorRepository.findById(doctorId).orElse(null);
	}

	@Named("findServiceById")
	protected Service findServiceById(Long serviceId) {
		return serviceRepository.findById(serviceId).orElse(null);
	}
}

