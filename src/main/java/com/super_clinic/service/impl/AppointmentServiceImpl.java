package com.super_clinic.service.impl;

import com.super_clinic.timetable.FreeTimeSlots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.super_clinic.dto.AppointmentDto;
import com.super_clinic.entity.Appointment;
import com.super_clinic.mapper.AppointmentMapper;
import com.super_clinic.repository.AppointmentRepository;
import com.super_clinic.service.BaseService;

import javax.management.Query;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl extends BaseService<Long, AppointmentDto, Appointment, AppointmentMapper, AppointmentRepository>{

	private AppointmentMapper appointmentMapper;

	@Autowired
	public AppointmentServiceImpl(AppointmentRepository repository, AppointmentMapper mapper, AppointmentMapper appointmentMapper) {
		super(repository, mapper);
        this.appointmentMapper = appointmentMapper;
    }

	public Long save(AppointmentDto appointmentDto) {
		List<FreeTimeSlots.TimeSlot> s = null;
		for(FreeTimeSlots.TimeSlot slots: s) {
			if(appointmentDto.getStart().isAfter(slots.start) && appointmentDto.getEnd().isBefore(slots.end)) {
				var app = mapper.dtoToEntity(appointmentDto);
				return repository.save(app).getId();

			}
		}
		return  null;
	}

	public void sortByDay(LocalDateTime date) {

	}

	public List<AppointmentDto> findAppointmentByStartTime(LocalDateTime date) {
		List<AppointmentDto> appointmentDtos = appointmentMapper.entitiesToDtos(repository.findAll());
		return appointmentDtos;
	}
}
