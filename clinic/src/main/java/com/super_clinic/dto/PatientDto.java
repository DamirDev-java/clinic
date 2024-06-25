package com.super_clinic.dto;

import java.util.ArrayList;
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
public class PatientDto {
	
	private Long id;

    private PersonalInfo personalInfo;
	
	private String diagnosis;
	
	private Long doctorId;
	
	private String username;
	
	private String password;
	
	@Builder.Default
	private List<UserRoleDto> userRoleDtos = new ArrayList<>();
}
