package com.assignment3.springboot.service;

import com.assignment3.springboot.model.User;
import com.assignment3.springboot.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
