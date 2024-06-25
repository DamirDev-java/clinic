package com.super_clinic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.super_clinic.dto.AppointmentDto;
import com.super_clinic.entity.Appointment;
import com.super_clinic.mapper.AppointmentMapper;
import com.super_clinic.repository.AppointmentRepository;
import com.super_clinic.repository.BaseRepository;
import com.super_clinic.service.BaseService;

@Service
@Transactional
public class AppointmentServiceImpl extends BaseService<Long, AppointmentDto, Appointment, AppointmentMapper, AppointmentRepository>{

	@Autowired
	public AppointmentServiceImpl(AppointmentRepository repository, AppointmentMapper mapper) {
		super(repository, mapper);
	}

	public Long save(AppointmentDto appointmentDto) {
		var app = mapper.dtoToEntity(appointmentDto);
		return repository.save(app).getId();
		}
}
