package com.super_clinic.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends GenericFilterBean{

	private final JwtTokenProvider jwtTokenProvider;
	
	
	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}


	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
	    Cookie[] cookies = httpRequest.getCookies();

		if(cookies==null) {
			cookies = new Cookie[0];
		}
		String token = null;
		 for (Cookie cookie : cookies) {
             if (cookie.getName().equals("jwtToken")) {
            	 token = cookie.getValue();
             }
         }

		try {
			if(token!=null && jwtTokenProvider.validateToken(token)) {
				Authentication authentication = jwtTokenProvider.getAuthentication(token);
				System.out.println(authentication);
				if(authentication!=null) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}catch (JwtAuthenticationException e) {
			SecurityContextHolder.clearContext();
			throw new JwtAuthenticationException("JWT tolen is expired or invalid");
		}
		chain.doFilter(request, response);
		
	}

	
}
