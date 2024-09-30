package com.super_clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@NoRepositoryBean
public interface BaseUserRepository<E, K> extends BaseRepository<E, K> {
    Optional<E> findByUsername(String username);
}
