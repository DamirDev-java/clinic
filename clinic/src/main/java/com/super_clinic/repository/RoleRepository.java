package com.super_clinic.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.entity.Role;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class RoleRepository extends BaseRepository<Long, Role>{

	private final EntityManager session;

	@Autowired
	public RoleRepository(EntityManager session) {
	    super(session, Role.class);
	    this.session = session;
	}

	 public Optional<Role> findByName(String name) {
		 Query query = session.createQuery("SELECT e FROM Role e WHERE e.role = :name", Role.class);
		 query.setParameter("name", name);
		 Role e = (Role) query.getSingleResult();
	     return Optional.ofNullable(e); 
	 }
}
