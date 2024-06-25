  package com.super_clinic.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.super_clinic.dto.AppointmentDto;
import com.super_clinic.dto.DoctorDto;
import com.super_clinic.dto.PatientDto;
import com.super_clinic.dto.ServiceDto;
import com.super_clinic.security.MyUserDetails;
import com.super_clinic.service.impl.AppointmentServiceImpl;
import com.super_clinic.service.impl.DoctorServiceImpl;
import com.super_clinic.service.impl.PatientServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	DoctorServiceImpl doctorServiceImpl;
	
	@Autowired
	PatientServiceImpl patientServiceImpl;
	
	@Autowired
    AppointmentServiceImpl appointmentServiceImpl;
	
	@GetMapping("/new/{serviceId}/{doctorId}")
	public String makeAnAppointment(
			@PathVariable("serviceId") Long serviceId,
			@PathVariable("doctorId") Long doctorId,
			Model model) {
		
		model.addAttribute("doctorId", doctorId);
		model.addAttribute("serviceId", serviceId);
		return "appointment";
	}
	
	@PostMapping("/save/{serviceId}/{doctorId}")
	public String saveAnAppointment(
			@PathVariable("serviceId") Long serviceId,
			@PathVariable("doctorId") Long doctorId,
			@RequestParam("appointmentDate") String date,
			Model model) {
	
		LocalDate localDate = LocalDate.parse(date);
		Date appDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PatientDto patient = patientServiceImpl.findByUsername(userDetails.getUsername()).get();
		
		AppointmentDto appointmentDto = AppointmentDto.builder()
				.doctorId(doctorId)
				.patientId(patient.getId())
				.serviceId(serviceId)
				.date(appDate)
				.build();
		
		appointmentServiceImpl.save(appointmentDto);
		return "home";
	}
	
}
  