package com.super_clinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.super_clinic.dto.RoleDto;
import com.super_clinic.entity.Role;
import com.super_clinic.mapper.RoleMapper;
import com.super_clinic.repository.RoleRepository;
import com.super_clinic.service.BaseService;

@Service
@Transactional
public class RoleServiceImpl extends BaseService<Long, RoleDto, Role, RoleMapper, RoleRepository>{

	@Autowired
	public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
		super(repository, mapper);
	}


	public Long save(RoleDto e) {
		var role = mapper.dtoToEntity(e);
		return repository.save(role).getId();
	}
}
