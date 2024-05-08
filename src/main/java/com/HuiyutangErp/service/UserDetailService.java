package com.HuiyutangErp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.HuiyutangErp.config.UserInfoUserDetails;
import com.HuiyutangErp.pojo.User;
import com.HuiyutangErp.repository.UserRepository;

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
