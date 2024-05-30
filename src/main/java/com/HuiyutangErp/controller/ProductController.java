package com.HuiyutangErp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	
	
	@GetMapping("/productList")
	public String productList() {
		
		return "admin/productList";
	}

}
