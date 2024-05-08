package com.example.HuiYuTang.HuiYuTang.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.HuiYuTang.HuiYuTang.config.UserInfoUserDetails;
import com.example.HuiYuTang.HuiYuTang.pojo.User;
import com.example.HuiYuTang.HuiYuTang.repository.UserRepository;

@Component
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> byUsername = userRepository.findByUsername(username);
		
		
		return byUsername.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User is not exist"));
	}

}
