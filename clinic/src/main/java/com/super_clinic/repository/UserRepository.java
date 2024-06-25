package com.super_clinic.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.super_clinic.entity.User;

import jakarta.persistence.EntityManager;

@Repository
public class UserRepository extends BaseRepository<Long, User>{
	
	@Autowired
	public UserRepository(EntityManager session) {
		super(session, User.class);
	}

}
