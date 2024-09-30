package com.super_clinic.dto;

import java.util.ArrayList;
import java.util.List;

import com.super_clinic.entity.PersonalInfo;
import com.super_clinic.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
	
    private Long id;
	
	private String role;
	
	@Builder.Default
	private List<UserRoleDto> userRoleDtos = new ArrayList<>();
	
}
