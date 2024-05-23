package com.HuiyutangErp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HuiyutangErp.pojo.Product;
import com.HuiyutangErp.repository.ProductRepository;

import io.jsonwebtoken.lang.Collections;


/**
 * 出貨
 * @author hank
 *
 */
@Controller
@RequestMapping("/admin/call")
public class CallController {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@GetMapping("/callformList")
	public String shipPage(Model mod) {
		return "admin/callformList";
	}
	
	@GetMapping("/callform")
	public String form(Model mod) {
		return "admin/callform";
	}
	

	
}