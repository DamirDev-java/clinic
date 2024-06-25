package com.super_clinic.dto;

import java.util.ArrayList;
import java.util.List;

import com.super_clinic.entity.Appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceDto {
	
	private Long id;
	
	private String serviceName;
	
	private int price;
	
	private Long doctorId;

	@Builder.Default
	private List<DoctorServiceDto> doctorServiceDtos = new ArrayList<>();
	
	@Builder.Default
	private List<AppointmentDto> appointmentDtos = new ArrayList<>();
}
