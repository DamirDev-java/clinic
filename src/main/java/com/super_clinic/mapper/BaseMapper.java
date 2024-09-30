package com.super_clinic.mapper;

import java.util.Collection;
import java.util.List;

public interface BaseMapper<E, T> {

    E dtoToEntity(T dto);

    List<E> dtosToEntity(List<T> dtos);

    T entityToDto(E entity);

    List<T> entitiesToDtos(List<E> entities);
}
