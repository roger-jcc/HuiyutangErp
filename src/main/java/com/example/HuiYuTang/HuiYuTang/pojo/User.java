package com.example.HuiYuTang.HuiYuTang.pojo;

import java.sql.Timestamp;
import java.time.Instant;

import com.example.HuiYuTang.HuiYuTang.bean.UserReq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Created by limi on 2017/10/14.
 */

@Data
@Entity
@Table(name = "User")
public class User  {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Long id;
	
	
	@Column(name = "USER_NAME")
    private String username;
	
	@Column(name = "PASSWORD")
    private String password;
	
	@Column(name = "EMAIL")
    private String email;
	
	
	@Column(name = "CREATED_TIME")
    private Timestamp createdTime;
	
	@Column(name = "UPDATED_TIME")
    private Timestamp updatedTime;
	
	//ADMIN,USER
	@Column(name = "ROLES")
	private String roles;
	


    
    public void create(UserReq req) {
    	
    	this.username = req.getUsername();
    	this.password = req.getPassword();
    	this.email = req.getEmail();
    	this.roles = req.getRoles();
    	this.createdTime = Timestamp.from(Instant.now());
    	
    			
    }


  
}
