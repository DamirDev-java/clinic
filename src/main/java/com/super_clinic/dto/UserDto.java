package com.super_clinic.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class UserDto {

	private Long id;
	
	private String username;
	
	private String password;
	
	@Builder.Default
	private List<UserRoleDto> userRoleDtos = new ArrayList<>();
}                             
