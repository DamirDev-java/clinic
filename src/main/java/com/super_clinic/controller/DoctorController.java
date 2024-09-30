package com.super_clinic.controller;

import com.super_clinic.dto.DoctorDto;
import com.super_clinic.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import com.super_clinic.entity.State;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.super_clinic.service.impl.DoctorServiceImpl;
import com.super_clinic.service.impl.ServiceServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
@RequestMapping("/api/v1/clinic")
public class DoctorController {

	DoctorServiceImpl doctorService;
	ServiceServiceImpl serviceService;

	@Autowired
	public DoctorController(DoctorServiceImpl doctorService, ServiceServiceImpl serviceService) {
		this.doctorService = doctorService;
		this.serviceService = serviceService;
	}

	@RequestMapping("/doctors")
	public ResponseEntity<List<DoctorDto>> hello() {
		try {
			var doctors = doctorService.findAll();
			return ResponseEntity.ok(doctors);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

}
