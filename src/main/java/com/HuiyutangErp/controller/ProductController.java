package com.HuiyutangErp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.HuiyutangErp.dto.ProductDto;
import com.HuiyutangErp.mapper.ProductMapper;
import com.HuiyutangErp.pojo.Product;
import com.HuiyutangErp.service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/productList")
	public String productList(Model model, 
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) {
		 Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = productService.findAllProduct(pageable);
		Page<ProductDto> pdto =	productPage.map(ProductMapper::toDto);

		model.addAttribute("productPage", pdto);
		return "admin/productList";
	}

}
