package com.super_clinic.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.dto.PatientDto;
import com.super_clinic.dto.ServiceDto;
import com.super_clinic.entity.Doctor;
import com.super_clinic.entity.Patient;
import com.super_clinic.entity.Service;
import com.super_clinic.repository.DoctorRepository;

@Mapper(componentModel = "spring", uses = {DoctorServiceMapper.class, AppointmentMapper.class})
public interface ServiceMapper extends BaseMapper<Service, ServiceDto>{
	

	@Mapping(source = "doctorServices", target = "doctorServiceDtos")
	@Mapping(source = "appointments", target = "appointmentDtos")
	ServiceDto entityToDto(Service service);

	@Override
	@Mapping(source = "doctorServiceDtos", target = "doctorServices")
	@Mapping(source = "appointmentDtos", target = "appointments")
    Service dtoToEntity (ServiceDto serviceDto);

	@Override
	List<Service> dtosToEntity(List<ServiceDto> dtos);

	@Override
	List<ServiceDto> entitiesToDtos(List<Service> entities);
	
    

}
