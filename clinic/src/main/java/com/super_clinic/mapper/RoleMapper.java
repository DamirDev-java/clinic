package com.super_clinic.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.super_clinic.dto.RoleDto;
import com.super_clinic.entity.Role;



@Mapper(componentModel = "spring", uses = {UserRoleMapper.class})
public interface RoleMapper extends BaseMapper<Role, RoleDto>{

	Role dtoToEntity (RoleDto roleCreateDto);
	
	@Mapping(source = "userRoles", target = "userRoleDtos")
	RoleDto entityToDto (Role role);

	@Override
	List<Role> dtosToEntity(List<RoleDto> dtos);
	
	public Role map(Long value);
	

	@Override
	List<RoleDto> entitiesToDtos(List<Role> entities);
	
	
}
