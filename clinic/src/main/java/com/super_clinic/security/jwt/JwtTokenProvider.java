package com.super_clinic.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.super_clinic.entity.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {
	
	private final UserDetailsService userDetailsService;
	
	@Autowired
	public JwtTokenProvider(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Value("${jwt.secret}")
	private String secretKey;
	
	@Value("${jwt.header}")
	private String authorizationHeader;
	
	@Value("${jwt.expiration}")
	private Long validityInMilliseconds;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createAccessToken(String username, List<String> roles) {
		Claims claims = (Claims) Jwts.claims().setSubject(username);
		claims.put("roles", roles);
		Date now = new Date(System.currentTimeMillis());
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				 .signWith(SignatureAlgorithm.HS256, secretKey)
	                .compact();
	}
	
	
//	public String createRefreshToken(String username) {
//		var user = userDetailsService.loadUserByUsername(username);
//		
//		
//	}
	
	 public boolean validateToken(String token) {
	        try {
	            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

	            if (claims.getBody().getExpiration().before(new Date())) {
	                return false;
	            }

	            return true;
	        } catch (JwtException | IllegalArgumentException e) {
	            throw new JwtAuthenticationException("JWT token is expired or invalid");
	        }
	    }
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
		
	}
	
	
	public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
	
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader(authorizationHeader);
	}
}