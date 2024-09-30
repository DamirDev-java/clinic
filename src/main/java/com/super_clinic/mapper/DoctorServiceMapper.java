package com.super_clinic.mapper;

import java.util.List;

import com.super_clinic.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.super_clinic.dto.DoctorServiceDto;
import com.super_clinic.entity.Doctor;
import com.super_clinic.entity.DoctorService;
import com.super_clinic.repository.DoctorRepository;
import com.super_clinic.repository.ServiceRepository;
import com.super_clinic.repository.UserRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class DoctorServiceMapper implements BaseMapper<DoctorService, DoctorServiceDto> {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	@Mapping(source = "doctorId", target = "doctor", qualifiedByName = "findDoctorById")
	@Mapping(source = "serviceId", target = "service", qualifiedByName = "findServiceById")
	public abstract DoctorService dtoToEntity(DoctorServiceDto dto);

	@Override
	public abstract List<DoctorService> dtosToEntity(List<DoctorServiceDto> dtos);

	@Override
	@Mapping(source = "doctor.id", target = "doctorId")
	@Mapping(source = "service.id", target = "serviceId")
	public abstract DoctorServiceDto entityToDto(DoctorService entity);

	@Override
	public abstract List<DoctorServiceDto> entitiesToDtos(List<DoctorService> entities);

	@Named("findDoctorById")
	protected Doctor findDoctorById(Long doctorId) {
		return doctorRepository.findById(doctorId).orElse(null);
	}

	@Named("findServiceById")
	protected Service findServiceById(Long serviceId) {
		return serviceRepository.findById(serviceId).orElse(null);
	}
}

