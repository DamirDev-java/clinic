package com.super_clinic.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.super_clinic.dto.AppointmentDto;
import com.super_clinic.entity.Appointment;
import com.super_clinic.repository.DoctorRepository;
import com.super_clinic.repository.PatientRepository;
import com.super_clinic.repository.ServiceRepository;
import com.super_clinic.service.impl.DoctorServiceImpl;
import com.super_clinic.service.impl.PatientServiceImpl;
import com.super_clinic.service.impl.ServiceServiceImpl;

@Mapper(componentModel = "spring", uses = {PatientRepository.class, DoctorRepository.class, ServiceRepository.class})
public interface AppointmentMapper extends BaseMapper<Appointment, AppointmentDto>{

	@Override
	@Mapping(source = "patientId", target = "patient")
	@Mapping(source = "doctorId", target = "doctor")
	@Mapping(source = "serviceId", target = "service")
	Appointment dtoToEntity(AppointmentDto dto);

	@Override
	List<Appointment> dtosToEntity(List<AppointmentDto> dtos);

	@Override
	AppointmentDto entityToDto(Appointment entity);

	@Override
	List<AppointmentDto> entitiesToDtos(List<Appointment> entities);

}
