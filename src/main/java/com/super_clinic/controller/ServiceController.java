package com.super_clinic.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.super_clinic.dto.DoctorDto;
import com.super_clinic.dto.DoctorServiceDto;
import com.super_clinic.dto.ServiceDto;
import com.super_clinic.entity.State;
import com.super_clinic.security.MyUserDetails;
import com.super_clinic.service.impl.DoctorServiceImpl;
import com.super_clinic.service.impl.DoctorServiceServiceImpl;
import com.super_clinic.service.impl.ServiceServiceImpl;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/api/v1/clinic")
public class ServiceController {

	private DoctorDto selectedDoctor = null;
	
	State status = State.CHOOSE_SERVICE;
	
	@Autowired
	ServiceServiceImpl serviceService;

	@Autowired
	DoctorServiceImpl doctorService;
	
	@Autowired
	DoctorServiceServiceImpl doctorServiceService;



	
	@PostMapping("/service/save")
	public String saveService(@ModelAttribute("service") ServiceDto serviceDto) {
		
		Long serviceId = serviceService.save(serviceDto);
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		
		Long doctorId = doctorService.findByUsername(username).get().getId();
		
		
		
		DoctorServiceDto doctorServiceDto = DoctorServiceDto.builder()
				.doctorId(doctorId)
				.serviceId(serviceId)
				.build();
		doctorServiceService.save(doctorServiceDto);
		return "home";
	}
	@RequestMapping("/services")
	public String services(Model model) {
		List<ServiceDto> serviceDtos =  serviceService.findAll();
		model.addAttribute("services", serviceDtos);

		return null;
	}

}
 