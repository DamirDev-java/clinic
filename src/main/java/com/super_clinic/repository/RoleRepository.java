package com.super_clinic.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

}
