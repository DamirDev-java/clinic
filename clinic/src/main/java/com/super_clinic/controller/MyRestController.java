package com.super_clinic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.super_clinic.dto.DoctorDto;

@RestController
public class MyRestController {
	
	@GetMapping("/test")
	public DoctorDto get() {
		return DoctorDto.builder().build();
	}

}
