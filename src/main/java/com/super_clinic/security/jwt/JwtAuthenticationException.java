package com.super_clinic.security.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import lombok.Getter;

@Getter
public class JwtAuthenticationException extends AuthenticationException{
	
    private HttpStatus httpStatus;
    
	public JwtAuthenticationException(String msg) {
		super(msg);
	}
	
	public JwtAuthenticationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
		super(msg);
		this.httpStatus = httpStatus;
	}
	
}
