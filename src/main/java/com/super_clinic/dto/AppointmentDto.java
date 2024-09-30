package com.super_clinic.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.super_clinic.entity.PersonalInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {

	private Long id;
	
	private Long patientId;
	
	private Long doctorId;
	
	private Long serviceId;

	private LocalDateTime start;

	private LocalDateTime end;

	public AppointmentDto (LocalDateTime start, LocalDateTime end, boolean isFree) {
		this.start = start;
		this.end = end;
		this.isFree = isFree;
	}

	private boolean isFree;
}
