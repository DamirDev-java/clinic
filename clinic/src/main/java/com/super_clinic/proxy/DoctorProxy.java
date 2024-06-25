package com.super_clinic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.hibernate.SessionFactory;

import com.super_clinic.service.impl.DoctorServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DoctorProxy implements InvocationHandler{
	
	private final SessionFactory sessionFactory;
	private final DoctorServiceImpl obj;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		boolean isStarted = false;
		
		try {
		if(obj.getClass().isAnnotationPresent(Transactional.class)) {
			sessionFactory.getCurrentSession().beginTransaction();
			isStarted = true;
		}
		
		Object result = method.invoke(obj, args);
		
		 if (isStarted) {
             sessionFactory.getCurrentSession().getTransaction().commit();
         }

         return result;
         } catch (Throwable e) {
         // Откатываем транзакцию в случае исключения
             if (isStarted) {
                 sessionFactory.getCurrentSession().getTransaction().rollback();
               }
             throw e;
     }
	}
}
