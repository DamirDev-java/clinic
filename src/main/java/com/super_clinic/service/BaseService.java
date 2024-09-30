 package com.super_clinic.service;

import com.super_clinic.mapper.BaseMapper;
import com.super_clinic.repository.BaseRepository;
import com.super_clinic.repository.BaseUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


 public class BaseService<K extends Serializable, D, E, Mapper extends BaseMapper<E, D>, Repository extends BaseRepository<E, K>> implements Service<K, D> {

	 public final BaseRepository<E, K> repository;
	 public final Mapper mapper;

	 public BaseService(Repository repository, Mapper mapper) {
		 this.mapper = mapper;
		 this.repository = repository;
	 }

	 @Override
	 public Optional<D> findById(K id) {
		 return repository.findById(id).map(mapper::entityToDto);
	 }

	 @Override
	 public void delete(D d) {
		 repository.delete(mapper.dtoToEntity(d));
	 }

	 @Override
	 public void update(D d) {
		 repository.save(mapper.dtoToEntity(d));
	 }

	 @Override
	 public List<D> findAll() {
		 return repository.findAll().stream().map(mapper::entityToDto).toList();
	 }

	 @Override
	 public Optional<D> findByUsername(String username) {
		 // Проверяем, является ли репозиторий экземпляром BaseUserRepository
		 if (repository instanceof BaseUserRepository) {
			 // Приводим тип и вызываем метод findByUsername
			 Optional<E> entity = ((BaseUserRepository<E, K>) repository).findByUsername(username);
			 return entity.map(mapper::entityToDto);
		 } else {
			 // Если репозиторий не поддерживает findByUsername
			 throw new UnsupportedOperationException("Repository does not support findByUsername");
		 }
	 }
 }

