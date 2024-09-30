package com.super_clinic.repository;

import com.super_clinic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.super_clinic.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@Repository
public interface ServiceRepository extends BaseRepository<Service, Long> {

}
