package com.super_clinic.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.dto.DoctorDto;
import com.super_clinic.dto.PatientDto;
import com.super_clinic.dto.UserRoleDto;
import com.super_clinic.entity.Doctor;
import com.super_clinic.entity.Patient;
import com.super_clinic.entity.UserRole;
import com.super_clinic.repository.DoctorRepository;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DoctorRepository.class, UserRoleMapper.class})
public interface PatientMapper extends BaseMapper<Patient, PatientDto>{

    @Mapping(source = "doctor.id", target= "doctorId")
    @Mapping(source = "userRoles", target= "userRoleDtos")
    PatientDto entityToDto(Patient patient);

    @Override
    List<PatientDto> entitiesToDtos(List<Patient> patients);

    @Mapping(source = "doctorId", target = "doctor")
    @Mapping(source = "userRoleDtos", target = "userRoles")
    Patient dtoToEntity(PatientDto patientDto);

	@Override
	List<Patient> dtosToEntity(List<PatientDto> dtos);
	
 
}



