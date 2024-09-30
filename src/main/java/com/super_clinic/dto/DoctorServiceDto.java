package com.super_clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorServiceDto {
	
	private Long id;
	
	private Long doctorId;
	
	private Long serviceId;

}
