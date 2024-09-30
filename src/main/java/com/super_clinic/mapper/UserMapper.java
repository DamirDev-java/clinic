package com.super_clinic.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.super_clinic.dto.UserDto;
import com.super_clinic.entity.User;

@Mapper(componentModel = "spring", uses = {UserRoleMapper.class})
public abstract class UserMapper implements BaseMapper<User, UserDto> {

	@Override
	public abstract User dtoToEntity(UserDto dto);

	@Override
	public abstract List<User> dtosToEntity(List<UserDto> dtos);

	@Override
	@Mapping(source = "userRoles", target = "userRoleDtos")
	public abstract UserDto entityToDto(User entity);

	@Override
	public abstract List<UserDto> entitiesToDtos(List<User> entities);
}

