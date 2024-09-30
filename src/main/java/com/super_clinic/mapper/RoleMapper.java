package com.super_clinic.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.super_clinic.dto.RoleDto;
import com.super_clinic.entity.Role;



@Mapper(componentModel = "spring", uses = {UserRoleMapper.class})
public abstract class RoleMapper implements BaseMapper<Role, RoleDto> {

	@Override
	public abstract List<Role> dtosToEntity(List<RoleDto> dtos);

	public abstract Role dtoToEntity(RoleDto roleCreateDto);

	@Override
	@Mapping(source = "userRoles", target = "userRoleDtos")
	public abstract RoleDto entityToDto(Role role);

	@Override
	public abstract List<RoleDto> entitiesToDtos(List<Role> entities);

}

