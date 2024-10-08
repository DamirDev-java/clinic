package com.super_clinic.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleDto {

	private Long id;
	
	private Long userId;
	
	private Long roleId;
}
