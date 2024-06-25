package com.super_clinic.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.super_clinic.dto.DoctorServiceDto;
import com.super_clinic.entity.Doctor;
import com.super_clinic.entity.DoctorService;
import com.super_clinic.repository.DoctorRepository;
import com.super_clinic.repository.ServiceRepository;
import com.super_clinic.repository.UserRepository;

@Mapper(componentModel = "spring", uses = {DoctorRepository.class, ServiceRepository.class})
public interface DoctorServiceMapper extends BaseMapper<DoctorService, DoctorServiceDto>{

	@Override
	@Mapping(source = "doctorId", target = "doctor")
	@Mapping(source = "serviceId", target = "service")
	DoctorService dtoToEntity(DoctorServiceDto dto);

	public DoctorService map(Long doctorId);
	
	@Override
	List<DoctorService> dtosToEntity(List<DoctorServiceDto> dtos);
	
	@Mapping(source = "doctor.id", target = "doctorId")
	@Mapping(source = "service.id", target = "serviceId")
	@Override
	DoctorServiceDto entityToDto(DoctorService entity);

	@Override
	List<DoctorServiceDto> entitiesToDtos(List<DoctorService> entities);

}
