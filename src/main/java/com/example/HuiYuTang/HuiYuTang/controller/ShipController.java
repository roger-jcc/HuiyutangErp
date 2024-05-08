package com.example.HuiYuTang.HuiYuTang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HuiYuTang.HuiYuTang.service.RestockService;


/**
 * 出貨
 * @author hank
 *
 */
@RestController
@RequestMapping(name ="api/ship" , produces = MediaType.APPLICATION_JSON_VALUE)
public class ShipController {
	
	@Autowired
	private RestockService restockService;
	
	
	@PostMapping("/ship")
	public String ship() {
		return null;
	}
}
