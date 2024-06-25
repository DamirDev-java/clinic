package com.super_clinic.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.super_clinic.dto.PatientDto;
import com.super_clinic.dto.UserRoleDto;
import com.super_clinic.entity.DoctorService;
import com.super_clinic.entity.User;
import com.super_clinic.security.MyUserDetails;
import com.super_clinic.service.impl.DoctorServiceImpl;
import com.super_clinic.service.impl.PatientServiceImpl;
import com.super_clinic.service.impl.RoleServiceImpl;
import com.super_clinic.service.impl.UserRoleServiceImpl;
import com.super_clinic.service.impl.UserServiceImpl;

@Controller
public class PatientController {
	
	private PatientServiceImpl patientService;
	
	private DoctorServiceImpl doctorService;
	
	private UserRoleServiceImpl userRoleService;
	
	
	@Autowired
	public PatientController(PatientServiceImpl patientService, DoctorServiceImpl doctorService,  UserRoleServiceImpl userRoleService) {
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.userRoleService = userRoleService;
	}

	@GetMapping("/patient/new")
	public String patientLogin(Model model) {
		PatientDto patientDto = new PatientDto();
		model.addAttribute("user", patientDto);
		return "patient-register";
	}
	
	@PostMapping("/register/save")
	public String saveWithDoctor(@ModelAttribute("user") PatientDto patientDto) {
		Long patientId = patientService.save(patientDto);
		UserRoleDto userRoleDto = UserRoleDto.builder()
				.roleId(1L)
				.userId(patientId)
				.build();
		userRoleService.save(userRoleDto);
		return "home";
	
	}
	

	@GetMapping("/patient/home")
	public String patientHome(Model model) {
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		    
		GrantedAuthority rolePatient = new SimpleGrantedAuthority("ROLE_PATIENT");
		   
		if (authorities.contains(rolePatient)) {
			var patientDto = patientService.findByUsername(username).orElse(null);
			model.addAttribute("patient", patientDto);
			return "personal-area";
		}
		else {
			var doctorDto = doctorService.findByUsername(username);
			model.addAttribute("doctorDto", doctorDto);	
			return "admin-panel";
		}
		
	}

}
