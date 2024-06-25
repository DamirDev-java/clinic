package com.super_clinic.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.super_clinic.Weather;
import com.super_clinic.dto.PatientDto;
import com.super_clinic.service.impl.UserServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.super_clinic.dto.UserDto;
import com.super_clinic.security.jwt.JwtTokenProvider;

@Controller
public class LoginController {
	
	Weather weather;
	UserDetailsService userService;
	PasswordEncoder passwordEncoder;
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public LoginController(Weather weather, UserDetailsService userService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		this.weather = weather;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/home") 
	public String homePage() {
		//weather.getWeather();
		return "home";
	}
	
	@PostMapping("/login") 
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletResponse response
			) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			UserDetails userDetails = userService.loadUserByUsername(username);
			String hashedPassword = userDetails.getPassword();
			if(passwordEncoder.matches(password, hashedPassword)) {
				String jwtToken = jwtTokenProvider.createAccessToken(username, userDetails.getAuthorities().stream().map(x -> x.toString()).toList());
				Cookie cookie = new Cookie("jwtToken", jwtToken);
				cookie.setMaxAge(36000); // Время жизни куки - 1 час
			    cookie.setPath("/"); // Доступность куки для всего приложения
			    response.addCookie(cookie);
			}
			else {
				throw new Exception("FuckYou");
			}
        } catch (Exception e) {
            
        }
		return "home";
	}
	
}
