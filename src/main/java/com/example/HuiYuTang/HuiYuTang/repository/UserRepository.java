package com.example.HuiYuTang.HuiYuTang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HuiYuTang.HuiYuTang.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
	
	User findByUsernameAndPassword(String username ,String password);
	
	
	Optional<User> findByUsername(String username);

}
