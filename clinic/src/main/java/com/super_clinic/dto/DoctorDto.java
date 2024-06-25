  package com.super_clinic.dto;

import java.util.ArrayList;
import java.util.List;

import com.super_clinic.entity.PersonalInfo;
import com.super_clinic.entity.UserRole;
import com.super_clinic.entity.DoctorService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {

	private Long id;
	
    private String username;
	
	private String password;
	
	private PersonalInfo personalInfo;
	
	private boolean ready; 
	
	private String specialisation;
	
	@Builder.Default
	private List<PatientDto> patientDtos = new ArrayList<>();
	
	@Builder.Default
	private List<DoctorServiceDto> doctorServiceDtos = new ArrayList<>();
	
	@Builder.Default
	private List<UserRoleDto> userRoleDtos = new ArrayList<>();
	

}
