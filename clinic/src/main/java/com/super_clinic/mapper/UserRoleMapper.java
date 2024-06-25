package com.super_clinic.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.dto.UserRoleDto;
import com.super_clinic.entity.Role;
import com.super_clinic.entity.UserRole;
import com.super_clinic.repository.RoleRepository;
import com.super_clinic.repository.UserRepository;

@Mapper(componentModel = "spring", uses = {RoleRepository.class, UserRepository.class})
public interface UserRoleMapper extends BaseMapper<UserRole, UserRoleDto>{

    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "user.id", target = "userId")
    UserRoleDto entityToDto(UserRole entityRole);

    
    @Mapping(source = "roleId", target = "role")
    @Mapping(source = "userId", target = "user")
    UserRole dtoToEntity(UserRoleDto entityRoleDTO);


	@Override
	List<UserRole> dtosToEntity(List<UserRoleDto> dtos);


	@Override
    List<UserRoleDto> entitiesToDtos(List<UserRole> entities);
    
    
        
}
