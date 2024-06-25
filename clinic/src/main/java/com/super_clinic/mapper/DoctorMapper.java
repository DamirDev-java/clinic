package com.super_clinic.mapper;


import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.super_clinic.dto.DoctorDto;
import com.super_clinic.entity.Doctor;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, DoctorServiceMapper.class, UserRoleMapper.class})
public interface DoctorMapper extends BaseMapper<Doctor, DoctorDto>{
	
    @Override
	public List<Doctor> dtosToEntity(List<DoctorDto> dtos);
    
    public Doctor map(Long doctorId);
    
	
    public DoctorDto entityToDto(Doctor doctor);

	@Override
    public List<DoctorDto> entitiesToDtos(List<Doctor> doctors);
     
	@Mapping(target = "patients", source = "patientDtos")
	@Mapping(target = "doctorServices", source = "doctorServiceDtos")
	@Mapping(target = "userRoles", source = "userRoleDtos")
    @Mapping(target = "isReady", source = "ready")
    Doctor dtoToEntity(DoctorDto doctorDto); 
} 
