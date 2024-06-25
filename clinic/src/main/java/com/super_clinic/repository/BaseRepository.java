package com.super_clinic.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseRepository<K extends Serializable, E> implements Repository<K, E>{
	
	private final  EntityManager session;
	
	private final Class<E> clazz;

	@Override
	public void delete(E e) {
		session.remove(e);
	}

	@Override
	public void update(E e) {
		session.merge(e);
	}

	@Override
	public E save(E e) {
		session.persist(e);
		return e;
    }

	@Override
	public Optional<E> findById(K id, Map<String, Object> properties) {
		return Optional.ofNullable(session.find(clazz, id, properties));
	}

	public EntityManager getSession() {
		return session;
	}

	@Override
	public List<E> findAll() {
		Query query = session.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e");
		List<E> e = query.getResultList();
		return e;
	}

	@Override
	public Optional<E> findByUsername(String username) {
	    Query query = session.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e "
	            + "WHERE username = :username");
	    query.setParameter("username", username);
	    
	    E e = (E) query.getSingleResult();
	    return Optional.ofNullable(e);
	    
	}

	@Override
	public E map(K id) {
		if(id == null) {
			return null;
		}
		return session.find(clazz, id);
	}


}
