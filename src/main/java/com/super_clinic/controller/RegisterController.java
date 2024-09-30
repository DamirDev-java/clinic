package com.super_clinic.controller;

import com.super_clinic.dto.DoctorDto;
import com.super_clinic.service.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/clinic")
public class RegisterController {

    private DoctorServiceImpl doctorService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(DoctorServiceImpl doctorService, PasswordEncoder passwordEncoder) {
        this.doctorService = doctorService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("doctor/register")
    public void register(@RequestParam("password") String password,
                         @RequestParam("username") String username) {

        String encodePassword = passwordEncoder.encode(password);

        DoctorDto doctorDto = DoctorDto.builder()
                .password(encodePassword)
                .username(username)
                .build();

        doctorService.save(doctorDto);
    }

}
