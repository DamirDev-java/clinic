package com.super_clinic.mapper;

import java.util.Collection;
import java.util.List;

import com.super_clinic.repository.DoctorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.super_clinic.dto.DoctorDto;
import com.super_clinic.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, DoctorServiceMapper.class, UserRoleMapper.class})
public abstract class DoctorMapper implements BaseMapper<Doctor, DoctorDto> {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public abstract List<Doctor> dtosToEntity(List<DoctorDto> dtos);

    public Doctor map(Long doctorId) {
        return doctorRepository.findById(doctorId).orElse(null);
    }

    public abstract DoctorDto entityToDto(Doctor doctor);

    @Override
    public abstract List<DoctorDto> entitiesToDtos(List<Doctor> doctors);

    @Mapping(target = "patients", source = "patientDtos")
    @Mapping(target = "doctorServices", source = "doctorServiceDtos")
    @Mapping(target = "userRoles", source = "userRoleDtos")
    @Mapping(target = "isReady", source = "ready")
    public abstract Doctor dtoToEntity(DoctorDto doctorDto);
}
