package com.super_clinic.mapper;


import java.util.List;

import com.super_clinic.entity.*;
import com.super_clinic.repository.DoctorRepository;
import com.super_clinic.repository.ServiceRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.dto.UserRoleDto;
import com.super_clinic.repository.RoleRepository;
import com.super_clinic.repository.UserRepository;

@Mapper(componentModel = "spring")
public abstract class UserRoleMapper implements BaseMapper<UserRole, UserRoleDto> {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "user.id", target = "userId")
    public abstract UserRoleDto entityToDto(UserRole entityRole);

    @Override
    @Mapping(source = "roleId", target = "role", qualifiedByName = "findRoleById")
    @Mapping(source = "userId", target = "user", qualifiedByName = "findUserById")
    public abstract UserRole dtoToEntity(UserRoleDto entityRoleDTO);

    @Override
    public abstract List<UserRole> dtosToEntity(List<UserRoleDto> dtos);

    @Override
    public abstract List<UserRoleDto> entitiesToDtos(List<UserRole> entities);

    @Named("findRoleById")
    protected Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Named("findUserById")
    protected User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}

