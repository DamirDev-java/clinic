package com.super_clinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.super_clinic.dto.DoctorDto;
import com.super_clinic.dto.DoctorServiceDto;
import com.super_clinic.dto.ServiceDto;
import com.super_clinic.entity.Doctor;
import com.super_clinic.entity.Service;
import com.super_clinic.mapper.DoctorMapper;
import com.super_clinic.mapper.ServiceMapper;
import com.super_clinic.repository.DoctorRepository;
import com.super_clinic.repository.ServiceRepository;
import com.super_clinic.service.BaseService;


@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl  extends BaseService<Long, ServiceDto, Service, ServiceMapper, ServiceRepository>{
	
	DoctorMapper doctorMapper;
	
	@Autowired
	public ServiceServiceImpl(ServiceRepository repository, ServiceMapper mapper, DoctorMapper doctorMapper) {
		super(repository, mapper);
		this.doctorMapper = doctorMapper;
	}

	public Long save(ServiceDto e) {
		var service = mapper.dtoToEntity(e);
		return repository.save(service).getId();
	}
	
	public List<DoctorDto> findDoctorsByServiceId(Long id) {
		var service = repository.findById(id).get();
		List<DoctorDto> doctorDtos = service.getDoctorServices().stream().map((e) -> e.getDoctor()).map(doctorMapper::entityToDto).toList();
		return doctorDtos;
	}



}
