package com.super_clinic.repository;

import jakarta.persistence.EntityNotFoundException;
import org.checkerframework.checker.units.qual.K;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E, K> extends JpaRepository<E, K> {

}
