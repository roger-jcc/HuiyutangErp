package com.example.HuiYuTang.HuiYuTang.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.HuiYuTang.HuiYuTang.bean.UserReq;
import com.example.HuiYuTang.HuiYuTang.pojo.User;
import com.example.HuiYuTang.HuiYuTang.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;

	public User checkuser(String username ,String password) throws Exception{
		User user = check(username,password);
		return user;
	}
	
	

	@SuppressWarnings("deprecation")
	private User check(String username, String password) throws Exception {
		
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			User u = user.get();
			String encodepassword = u.getPassword();
			if( passwordEncoder.matches(password, encodepassword)) {
				return u ;
			}else {
				throw new Exception("登入失敗，密碼錯誤");
			}
		}else {
			throw new Exception("登入失敗，查無此使用者");
		}
	
	}
	
	public boolean findUserByUserName(String username) throws Exception {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
				return true;
		}else {
			return false;
		}
	}



	public void save(UserReq req) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		User user = new User();
		user.create(req);
		user.setPassword(passwordEncoder.encode(user.getPassword()) );
		userRepository.save(user);
	}
	
	
	
	
}
