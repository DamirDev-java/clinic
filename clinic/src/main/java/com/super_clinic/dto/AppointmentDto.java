package com.super_clinic.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	private Date date;
	
	
}
