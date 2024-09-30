package com.super_clinic.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.super_clinic.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.super_clinic.dto.DoctorDto;
import com.super_clinic.entity.Doctor;
import com.super_clinic.repository.DoctorRepository;
import com.super_clinic.repository.PatientRepository;
import com.super_clinic.service.BaseService;
import com.super_clinic.exception.NotFoundByIdException;

@Transactional
@Service
public class DoctorServiceImpl extends BaseService<Long, DoctorDto, Doctor, DoctorMapper, DoctorRepository>{

	@Autowired
	public DoctorServiceImpl(DoctorRepository repository, DoctorMapper mapper) {
		super(repository, mapper);
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Long save(DoctorDto e) {
		var doctor = mapper.dtoToEntity(e);
		doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
		return repository.save(doctor).getId();
	}

	
}
