package com.super_clinic.mapper;

import com.super_clinic.dto.PatientDto;
import com.super_clinic.entity.Doctor;
import com.super_clinic.entity.Patient;
import com.super_clinic.mapper.BaseMapper;
import com.super_clinic.repository.DoctorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PatientMapper implements BaseMapper<Patient, PatientDto> {

    @Autowired
    protected DoctorRepository doctorRepository;

    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "userRoles", target = "userRoleDtos")
    public abstract PatientDto entityToDto(Patient patient);

    @Override
    public abstract List<PatientDto> entitiesToDtos(List<Patient> patients);

    @Mapping(source = "doctorId", target = "doctor", qualifiedByName = "doctorIdToDoctor")
    @Mapping(source = "userRoleDtos", target = "userRoles")
    public abstract Patient dtoToEntity(PatientDto patientDto);

    @Override
    public abstract List<Patient> dtosToEntity(List<PatientDto> dtos);

    @Named("doctorIdToDoctor")
    protected Doctor doctorIdToDoctor(Long doctorId) {
        return doctorRepository.findById(doctorId).orElse(null);
    }
}
