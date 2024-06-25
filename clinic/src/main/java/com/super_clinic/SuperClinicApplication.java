package com.super_clinic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.super_clinic.dto.DoctorDto;
import com.super_clinic.dto.DoctorServiceDto;
import com.super_clinic.dto.UserRoleDto;
import com.super_clinic.dto.PatientDto;
import com.super_clinic.dto.RoleDto;
import com.super_clinic.dto.ServiceDto;
import com.super_clinic.entity.Patient;
import com.super_clinic.entity.PersonalInfo;
import com.super_clinic.entity.Service;
import com.super_clinic.entity.User;
//import com.super_clinic.mapper.DoctorMapper;
//import com.super_clinic.mapper.PatientMapper;
//import com.super_clinic.mapper.RoleMapper;
//import com.super_clinic.mapper.UserRoleMapper;
import com.super_clinic.repository.UserRoleRepository;
import com.super_clinic.security.jwt.JwtTokenProvider;
import com.super_clinic.repository.ServiceRepository;
import com.super_clinic.repository.UserRepository;
//import com.super_clinic.service.impl.DoctorServiceImpl;
//import com.super_clinic.service.impl.DoctorServiceServiceImpl;
//import com.super_clinic.service.impl.UserRoleServiceImpl;
//import com.super_clinic.service.impl.PatientServiceImpl;
//import com.super_clinic.service.impl.RoleServiceImpl;
//import com.super_clinic.service.impl.ServiceServiceImpl;


@SpringBootApplication

public class SuperClinicApplication {
   
	
    public static void main(String[] args) {
        // = new  AnnotationConfigApplicationContext(Config.class);
    	
   ApplicationContext ctx = SpringApplication.run(SuperClinicApplication.class, args); 
   
    var jwt = ctx.getBean(JwtTokenProvider.class);
    System.out.println(jwt.createAccessToken("89643271847", Arrays.asList("ROLE_DOCTOR")));
    
     
//   var serviceService = ctx.getBean(ServiceServiceImpl.class);
//   var userRoleService = ctx.getBean(UserRoleServiceImpl.class);
//   var patientService =  ctx.getBean(PatientServiceImpl.class);
//   var doctorService =  ctx.getBean(DoctorServiceImpl.class);
//   var roleService = ctx.getBean(RoleServiceImpl.class);
//   var doctorServiceService =  ctx.getBean(DoctorServiceServiceImpl.class);
//   
//   var userRepository = ctx.getBean(UserRepository.class);
//   var patientMapper = ctx.getBean(PatientMapper.class);
//   var userRoleMapper = ctx.getBean(UserRoleMapper.class);
   
//   var doctorDto = DoctorDto.builder()
//		   .username("89643271847")
//		   .password("funtik")
//		   .ready(true)
//		   .specialisation("dentist")
//		   .personalInfo(PersonalInfo.builder()
//				   .name("Elena")
//				   .surname("Chabrava")
//		       .build())
//		  .build();
//   Long doctorId = doctorService.save(doctorDto);
//   
//   var userRoleDto = UserRoleDto.builder()
//		   .userId(doctorId)
//		   .roleId(2L)
//		   .build();
//   userRoleService.save(userRoleDto);
//   
//   var x = doctorService.findByUsername("89643271847");
//   System.out.println(x);
   
//   var doctorServiceDto = DoctorServiceDto.builder()
//		   .doctorId(2L)
//		   .serviceId(1L)
//		   .build();
//   
//   doctorServiceService.save(doctorServiceDto);
   
 
//   var patient = (Patient) userRepository.findByUsername("4").get();
//   
//  
//   var patientDto = patientMapper.entityToDto(patient);
//   System.out.println(patient);
		   
//   Optional<User> user = userRepository.findByUsername("89811994364");
//   
//   var patientDto = patientService.findById(1L).get();
//   
//   var serviceDto = ServiceDto.builder()
//		   .serviceName("Лечение кариеса")
//		   .price(2500)
//		   .build();
//   
//   Long serviceId = serviceService.save(serviceDto);
//   
//   var doctorDto = DoctorDto.builder()
//		   .username("89643271847")
//		   .password("funtik")
//		   .personalInfo(PersonalInfo.builder()
//				   .name("Elena")
//				   .surname("Chabrava")
//		       .build())
//		  .build();
//   
//   doctorDto.getPatientDtos().add(patientDto);
//   doctorDto.getServiceDtos().add(serviceDto);
//   
//   doctorService.save(doctorDto);
		   
   
//   var patientDto = PatientDto.builder()
//		  .username("89811994364") 
//		  .password("damir2005")
//		  .personalInfo(PersonalInfo.builder()
//				  .name("Damir")
//				  .surname("Kildeev")
//				  .build())
//		   .build();
//   
//   Long patientId = patientService.save(patientDto);
//   
//   var roleDto = RoleDto.builder()
//		   .role("ROLE_PATIENT")
//		   .build();
//   
//   Long roleId = roleService.save(roleDto);
//   
//   var userRoleDto = UserRoleDto.builder()
//		   .roleId(roleId)
//		   .userId(patientId)
//		   .build();
//   
//   userRoleService.save(userRoleDto);
   
    
//    var patientService = ctx.getBean(PatientServiceImpl.class);
//    var patientMapper = ctx.getBean(PatientMapper.class);
//    
//    var patientDto = patientService.findById(3L).get();
//    var patient = patientMapper.dtoToEntity(patientDto);
//    System.out.println(patient);
    
    
    //    var serviceRepository = ctx.getBean(ServiceService.class);
//    
//    var service = ServiceDto.builder()
//    		.price(2500)
//    		.serviceName("Лечение кариеса")
//    		.doctorId(1L)
//    		.build();
//    
//    serviceRepository.save(service);
   
   
         
    
//    System.out.println(user.get());
        //var doctorServiceImpl = ctx.getBean(DoctorServiceImpl.class);
////        var patientServiceImpl = ctx.getBean(PatientServiceImpl.class);
////        var roleServiceImpl = ctx.getBean(RoleServiceImpl.class); 
        //var userService = ctx.getBean(UserService.class);
        
//        User user = new User(null, "u", "u");
//        userService.saveUser(user);
//       // var entityRoleServieImpl = ctx.getBean(EntityRoleServiceImpl.class);
//        
//    	//var entityRoleRepository = ctx.getBean(EntityRoleRepository.class);
//    	
//    	//var roleMapper = ctx.getBean(RoleMapper.class); 
//    
//        var doctorDto = userService.findByUsername("89643271847");
//        
//        System.out.println(doctorDto);
//        var doctorMapper = ctx.getBean(DoctorMapper.class);
//        
//        var doctorDto = DoctorDto.builder()
//        		.personalInfo(PersonalInfo.builder()
//        				.name("Elena")
//        				.surname("Chabrava")
//        				.photoUrl("fe101feb-ae68-4ede-b4a0-cb0547ac9cb9.png")
//        				.build())
//        		.ready(true)
//        		.specialisation("Dentist")
//        		.username("89643271847")
//        		.password("funtik")
//        		.build();
//        
//        doctorServiceImpl.save(doctorDto);
 
       
       
//       
      
        
      //  System.out.println(doctorDtos);
//        
//        
////        
//         var role = roleServiceImpl.findById(2L);
//         var doctor = doctorServiceImpl.findById(1L);
         
        
//         System.out.println(role.get().getEntityRoleDtos());
        // var patient = patientServiceImpl.findById(1L);         
//         var doctor = doctorServiceImpl.findById(1L);
//         System.out.println(doctor.get().getPatientReadDtos());
//         
//         System.out.println(doctor.get().g);
         
         
//         var entityRole = EntityRoleDto.builder()
//        		 .entityId(doctor.get().getId())
//        		 .entityType("Doctor")
//        		 .roleId(role.get().getId())
//       		 .build();
//         
//         entityRoleServieImpl.save(entityRole);
        
//       var doctor = DoctorCreateDto .builder()
//    		   .isReady(true)
//    		   .personalInfo(PersonalInfo.builder()
//    				   .name("Elena")
//    				   .surname("Chabrava")
//    				   .build())
//    		   .build();
//       
//       doctorServiceImpl.save(doctor);
//        
//      
//      
    
//        var patient = PatientDto.builder()
//              .diagnosis("No")
//              .personalInfo(PersonalInfo.builder()
//                      .name("Mstr")
//                      .surname("X")
//                      .build())
//              .doctorId(doctorId)
//              .username("89811994364")
//              .password("damir2005")
//              .build();
//        
//        patientServiceImpl.save(patient);
        
    }
}
        
//        var role = RoleDto.builder()
//        		.role("USER")
//        		.build();
//        
//        roleServiceImpl.save(role);
        
//        var role = roleServiceImpl.findByName("USER");
//        var patient = patientServiceImpl.findById(1L);
//         
//        
//        var patientsRolesCreateDto = PatientsRolesCreateDto.builder()
//        		.role_id(role.get().getId())
//        		.patient_id(patient.get().getId())
//        		.build();
//
//         patientsRolesServiceImpl.save(patientsRolesCreateDto);
        
        
        //System.out.println(role);
        
        		
//        patientServiceImpl.save(patient);
    	

        
//        PatientServiceImpl s = ctx.getBean(PatientServiceImpl.class);
//        DoctorServiceImpl d = ctx.getBean(DoctorServiceImpl.class);
       
//        // Получение менеджера транзакций
//        PlatformTransactionManager transactionManager = ctx.getBean(PlatformTransactionManager.class);
//        
//        // Определение новой транзакции
//        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        
//        try {
//            var doctor = DoctorCreateDto.builder()
//                    .isReady(true)
//                    .personalInfo(PersonalInfo.builder()
//                            .name("Li")
//                            .surname("Vitalya")
//                            .build())
//                    .build();
            
//            var patient = PatientCreateDto.builder()
//                    .diagnosis("No")
//                    .personalInfo(PersonalInfo.builder()
//                            .name("Mstr")
//                            .surname("X")
//                            .build())
//                    .doctor_id(3L)
//                    .build();
//            
//            d.save(doctor);
//            s.save(patient);
            
//            // Подтверждение транзакции
//            transactionManager.commit(status);
//        } catch (Exception e) {
//            // Откат транзакции в случае исключения
//            transactionManager.rollback(status);
//            e.printStackTrace();
//        }
		
//		PatientService patientService = (PatientService) Proxy.newProxyInstance(
//                SessionFactory.class.getClassLoader(),
//                new Class[]{PatientService.class},
//                new PatientProxy(sessionFactory, patientServiceImpl));
				
		
		
				
//		try(var sessionFactory = ctx.getBean("sessionFactory", SessionFactory.class)) {
//		
//			
//		    var session = ctx.getBean("session", Session.class);
//			session.beginTransaction();
		    
//			var patientRepository = new PatientRepository(session);
//			var doctorRepository = new DoctorRepository(session);
			
//			var doctorReadMapper = new DoctorReadMapper();
//			var doctorCreateMapper = new DoctorCreateMapper();
//			
//			var patientReadMapper = new PatientReadMapper(doctorReadMapper);
//			var patientCreateMapper = new PatientCreateMapper(doctorRepository);
            
//           var patientServiceImpl = new PatientServiceImpl(patientRepository, patientReadMapper, patientCreateMapper);
//           var doctorServiceImpl = new DoctorServiceImpl(doctorRepository, doctorReadMapper, doctorCreateMapper);
		     
//			var patientServiceImpl = ctx.getBean(PatientServiceImpl.class);
//			var doctorServiceImpl = ctx.getBean(DoctorServiceImpl.class);
			
//            PatientService patientService = (PatientService) Proxy.newProxyInstance(
//		                SessionFactory.class.getClassLoader(),
//		                new Class[]{PatientService.class},
//		                new PatientProxy(sessionFactory, patientServiceImpl));
//		     
//	        		    
//		    DoctorService doctorService = (DoctorService) Proxy.newProxyInstance(
//		    		SessionFactory.class.getClassLoader(), 
//		    		new Class[] {DoctorService.class}, 
//		    		new DoctorProxy(sessionFactory, doctorServiceImpl));
//			doctorServiceImpl.findAll();    
//		   session.getTransaction().commit();
		   		                   
		
	//	}
		
//	}

//}
		
