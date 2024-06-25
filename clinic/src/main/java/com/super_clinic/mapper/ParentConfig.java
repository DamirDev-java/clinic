package com.super_clinic.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import com.super_clinic.entity.User;

@MapperConfig(
	    mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG
	)
	public interface ParentConfig {

	    // Not intended to be generated, but to carry inheritable mapping annotations:
	    @Mapping(target = "targetId", source = "id")
	    User entityToDto(User entity);

	}
