 package com.super_clinic.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.super_clinic.mapper.BaseMapper;
import com.super_clinic.repository.BaseRepository;

public class BaseService<K extends Serializable, D, E, Mapper extends BaseMapper<E, D>, Repository extends BaseRepository<K, E>> implements Service<K, D>{
    
	public final Repository repository;
	
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
		repository.update(mapper.dtoToEntity(d));
	}


	@Override
	public List<D> findAll() {
		return repository.findAll().stream().map(mapper::entityToDto).toList();
	}

	@Override
	public Optional<D> findByUsername(String username) {
		Optional<E> e = repository.findByUsername(username);
		Optional<D> doctorDto = e.map(mapper::entityToDto);
		return doctorDto;
	}
}
