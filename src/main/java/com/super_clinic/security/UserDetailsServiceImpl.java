package com.super_clinic.security;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.super_clinic.entity.Doctor;
import com.super_clinic.entity.User;
import com.super_clinic.repository.DoctorRepository;
import com.super_clinic.repository.UserRoleRepository;
import com.super_clinic.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		return user.map(MyUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
	}


}
