package com.super_clinic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.super_clinic.dto.UserDto;
import com.super_clinic.entity.User;
import com.super_clinic.mapper.UserMapper;
import com.super_clinic.repository.UserRepository;
import com.super_clinic.service.BaseService;

@Service
public class UserServiceImpl extends BaseService<Long, UserDto, User, UserMapper, UserRepository>{

	@Autowired
	public UserServiceImpl(UserRepository repository, UserMapper mapper) {
		super(repository, mapper);
	}
	
}
