package com.HuiyutangErp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HuiyutangErp.pojo.Product;
import com.HuiyutangErp.repository.ProductRepository;

@RestController
@RequestMapping("/admin/shipdata")
public class ShipDataController {
	
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * 產品模糊查詢
	 * @param str
	 * @return
	 */
	@GetMapping("/findProduct")
	public Product findProduct(@RequestParam(name="productName" ,required = true) String str ,Model mod) {
		List<String> productNameList = new ArrayList<>();
		Optional<Product> product =  productRepository.findByProductName(str);
		Product pro = new Product();
		if(product.isPresent()) {
			pro = product.get();
		}
		
		return pro;
	}
	
	

}
