package com.super_clinic.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.super_clinic.dto.UserDto;
import com.super_clinic.entity.User;

@Mapper(componentModel = "spring", uses = {UserRoleMapper.class})
public interface UserMapper extends BaseMapper<User, UserDto>{

	@Override
	User dtoToEntity(UserDto dto);
	
	@Override
	List<User> dtosToEntity(List<UserDto> dtos);

	@Override
	@Mapping(source="userRoles", target = "userRoleDtos")
	UserDto entityToDto(User entity);

	@Override
	List<UserDto> entitiesToDtos(List<User> entities);

	
	
}
