package com.example.HuiYuTang.HuiYuTang.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginReq {
	
    private String username;
	
    private String password;
    
    private String remember;

}
