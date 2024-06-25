package com.super_clinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.super_clinic.dto.DoctorDto;
import com.super_clinic.dto.DoctorServiceDto;
import com.super_clinic.entity.Doctor;
import com.super_clinic.entity.DoctorService;
import com.super_clinic.entity.User;
import com.super_clinic.mapper.DoctorServiceMapper;
import com.super_clinic.repository.DoctorRepository;
import com.super_clinic.repository.DoctorServiceRepository;
import com.super_clinic.repository.UserRepository;
import com.super_clinic.service.BaseService;

@Service
@Transactional
public class DoctorServiceServiceImpl extends BaseService<Long, DoctorServiceDto, DoctorService, DoctorServiceMapper, DoctorServiceRepository>{

	@Autowired
	public DoctorServiceServiceImpl(DoctorServiceRepository repository, DoctorServiceMapper mapper) {
		super(repository, mapper);
	}

	
	public Long save(DoctorServiceDto e) {
		var doctorService = mapper.dtoToEntity(e);
		doctorService.getDoctor().getDoctorServices().add(doctorService);
		doctorService.getService().getDoctorServices().add(doctorService);
		return repository.save(doctorService).getId();
	}
	
	
	
}
