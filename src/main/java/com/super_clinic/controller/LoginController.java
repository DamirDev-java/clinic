package com.super_clinic.controller;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.super_clinic.dto.PatientDto;
import com.super_clinic.service.impl.UserServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.super_clinic.dto.UserDto;
import com.super_clinic.security.jwt.JwtTokenProvider;

@Controller
@RequestMapping("/api/v1/clinic")
public class LoginController {

	UserDetailsService userService;
	PasswordEncoder passwordEncoder;
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	public LoginController( UserDetailsService userService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@RequestMapping("home")
	public String home() {
		return "home";
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {

		String username = loginRequest.get("username");
		String password = loginRequest.get("password");

		try {
			UserDetails userDetails = userService.loadUserByUsername(username);

			if (passwordEncoder.matches(password, userDetails.getPassword())) {
				String jwtToken = jwtTokenProvider.createAccessToken(
						username,
						userDetails.getAuthorities().stream().map(x -> x.toString()).toList()
				);
				return ResponseEntity.ok(Collections.singletonMap("token", jwtToken));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
		}
	}


}
