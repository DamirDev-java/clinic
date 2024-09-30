package com.super_clinic.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.super_clinic.entity.User;

import jakarta.persistence.EntityManager;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long>, BaseUserRepository<User, Long> {

}
