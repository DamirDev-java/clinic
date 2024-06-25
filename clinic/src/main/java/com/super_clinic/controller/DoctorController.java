package com.super_clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.super_clinic.entity.State;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.super_clinic.service.impl.DoctorServiceImpl;
import com.super_clinic.service.impl.ServiceServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DoctorController {
	
	private Long selectedDoctorId = null;
	
	State status = State.CHOOSE_DOCTOR;

	DoctorServiceImpl doctorService;
	ServiceServiceImpl serviceService;
	
	@Autowired
	public DoctorController(DoctorServiceImpl doctorService, ServiceServiceImpl serviceService) {
		this.doctorService = doctorService;
		this.serviceService = serviceService;
	}
	
	@RequestMapping("/doctors")
	public String hello(Model model) {
		var doctor = doctorService.findAll();
		model.addAttribute("doctors", doctor);
		return "doctor-list";
	}
	
	@RequestMapping("/doctor/service/{serviceId}")
	public String doctorsById(Model model, 
			@PathVariable("serviceId") Long serviceId) {
	var doctorDtos = serviceService.findDoctorsByServiceId(serviceId);
	var selectedService = serviceService.findById(serviceId).get();
	
	model.addAttribute("doctors", doctorDtos);
	model.addAttribute("selectedService", selectedService);
	model.addAttribute("status", status);
	return "doctor-list";
	}

}
