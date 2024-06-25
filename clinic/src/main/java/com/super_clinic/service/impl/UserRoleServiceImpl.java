package com.super_clinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.super_clinic.dto.UserRoleDto;
import com.super_clinic.entity.Patient;
import com.super_clinic.entity.UserRole;
import com.super_clinic.mapper.UserRoleMapper;
import com.super_clinic.repository.UserRoleRepository;
import com.super_clinic.repository.RoleRepository;
import com.super_clinic.repository.UserRepository;
import com.super_clinic.service.BaseService;

@Service
@Transactional
public class UserRoleServiceImpl extends BaseService<Long, UserRoleDto, UserRole, UserRoleMapper, UserRoleRepository>{

	@Autowired
	public UserRoleServiceImpl(UserRoleRepository repository, UserRoleMapper mapper) {
		super(repository, mapper);
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Long save(UserRoleDto e) {
		var entityRole = mapper.dtoToEntity(e);
		var role = roleRepository.findById(e.getRoleId());
		var user = userRepository.findById(e.getUserId());
		role.get().getUserRoles().add(entityRole);
		user.get().getUserRoles().add(entityRole);
		return repository.save(entityRole).getId();
	}

}
