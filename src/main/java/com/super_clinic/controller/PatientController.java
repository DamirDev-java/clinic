package com.super_clinic.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/v1/clinic")
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
	
	@PostMapping("/register/save")
	public String saveWithDoctor(@ModelAttribute("user") PatientDto patientDto) {
		Long patientId = patientService.save(patientDto);
		UserRoleDto userRoleDto = UserRoleDto.builder()
				.roleId(1L)
				.userId(patientId)
				.build();
		userRoleService.save(userRoleDto);

		return null;
	
	}

}
