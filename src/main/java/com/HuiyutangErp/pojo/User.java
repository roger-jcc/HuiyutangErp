package com.HuiyutangErp.pojo;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.HuiyutangErp.bean.UserReq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "TITLE")
	private String title;//副理,會計,倉管,主任 user: 出貨 ,晚班
	
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
	private String role;
	
	
	 @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	        name = "user_role_mapping",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	    private Set<Role> roles;
	


    
    public void create(UserReq req) {
    	
    	this.name = req.getName();
    	this.title = req.getTitle();
    	this.username = req.getUsername();
    	this.password = req.getPassword();
    	this.email = req.getEmail();
    	if(StringUtils.equals(req.getTitle(), "執行長")||StringUtils.equals(req.getTitle(), "副理")
    			||StringUtils.equals(req.getTitle(), "主任")) {
    		this.role = "ROLE_ADMIN";
    	}else {
    		this.role = "ROLE_USER";
    	}
    	this.createdTime = Timestamp.from(Instant.now());
    	
    			
    }


  
}
