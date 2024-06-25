//package com.super_clinic;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//
//import org.hibernate.SessionFactory;
//
//import java.lang.reflect.Proxy;
//
//import org.hibernate.Session;
//
//@Configuration
//@ComponentScan
//@EnableTransactionManagement
//public class Config {
//    
//	
//	@Autowired
//    private EntityManager entityManager;
//
//    @Bean
//    public EntityManager entityManager() {
//        return this.entityManager;
//    }
//    
////    @Bean
////    public SessionFactory sessionFactory() {
////        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
////    }
////    
////    @Bean
////    public Session session(SessionFactory sessionFactory) {
////        return (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[] {Session.class},
////            (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
////    }
//}
//
