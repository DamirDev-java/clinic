//package com.super_clinic.proxy;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import org.hibernate.SessionFactory;
//import com.super_clinic.service.PatientService;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class PatientProxy implements InvocationHandler {
//
//    private final SessionFactory sessionFactory;
//    private final PatientService target;
//
//    
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//    	
//        boolean transactionStarted = false;
//
//        try {
//            // Открываем транзакцию, если класс помечен аннотацией @Transactional
//        	if (target.getClass().getDeclaredAnnotation(Transactional.class) != null) {
//                sessionFactory.getCurrentSession().beginTransaction();
//                transactionStarted = true;
//            }
//
//            // Вызываем метод оригинального объекта
//            Object result = method.invoke(target, args);
//
//            // Коммитим транзакцию, если она была открыта
//            if (transactionStarted) {
//                sessionFactory.getCurrentSession().getTransaction().commit();
//            }
//
//            return result;
//        } catch (Throwable e) {
//            // Откатываем транзакцию в случае исключения
//            if (transactionStarted) {
//                sessionFactory.getCurrentSession().getTransaction().rollback();
//            }
//            throw e;
//        }
//    }
//   
	

