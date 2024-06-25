package com.super_clinic.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.super_clinic.security.UserDetailsServiceImpl;
import com.super_clinic.security.jwt.JwtConfigurer;
import com.super_clinic.security.jwt.LoginUrlAuthenticationEntryPoint;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtConfigurer jwtConfigurer;
    
    @Autowired
    private LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint;
	
	public SecurityConfig(JwtConfigurer jwtConfigurer) {
		this.jwtConfigurer = jwtConfigurer;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	     http.csrf(csrf -> csrf.disable())
	    		.cors().disable()
	            .authorizeHttpRequests(request -> request

	                .requestMatchers("/", "/login", "/doctors", "/services", "/home", "/patient/new", "/register/save", "/service/doctor/{}").permitAll()
                    
	                .requestMatchers("/patient/home").authenticated()
	               
	                .requestMatchers("/assets/**", "/forms/**", "/css/**", "/img/**", "/js/**", "/scss/**", "/vendor/**").permitAll()

	                .anyRequest().authenticated()
	             )
//	            .formLogin(form -> form
//	            		.loginPage("/login")
//	            		.defaultSuccessUrl("/doctors")
//	            )
	            .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
	            .exceptionHandling().authenticationEntryPoint(loginUrlAuthenticationEntryPoint).and()
	            
	            .apply(jwtConfigurer);
	            
	     return http.build();
	}
	
	 

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new UserDetailsServiceImpl();
//	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
             throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailsService());
//		provider.setPasswordEncoder(passwordEncoder());
//		return provider;
//	}
	
	
}
