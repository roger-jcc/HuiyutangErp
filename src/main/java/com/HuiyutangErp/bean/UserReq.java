package com.HuiyutangErp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserReq {
	
	private String name;
	
    private String username;
	
    private String password;
	
    private String checkPassword;
	
    private String email;
    
    private String title;
	
    private String roles;

}
