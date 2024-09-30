package com.super_clinic.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

@Repository
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {

} 
