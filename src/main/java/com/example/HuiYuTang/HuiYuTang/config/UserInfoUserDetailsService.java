package com.example.HuiYuTang.HuiYuTang.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.HuiYuTang.HuiYuTang.pojo.User;
import com.example.HuiYuTang.HuiYuTang.repository.UserRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byAccount = userRepository.findByUsername(username);
        return byAccount.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("not found user"));
    }
}