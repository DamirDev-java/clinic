package com.super_clinic.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

@Builder
public class UserDto {

	private Long id;
	
	private String username;
	
	private String password;
	
	@Builder.Default
	private List<UserRoleDto> userRoleDtos = new ArrayList<>();
}                             
