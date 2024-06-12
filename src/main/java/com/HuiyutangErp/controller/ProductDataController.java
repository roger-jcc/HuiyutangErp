package com.HuiyutangErp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HuiyutangErp.dto.ProductDto;
import com.HuiyutangErp.service.ProductService;

@RestController
@RequestMapping("/admin/productdata")
public class ProductDataController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/findProductList/{manufacterurId}")
    public List<ProductDto> findProductByManufacterurId(@PathVariable Long manufacterurId) {
		List<ProductDto> pdtoList = productService.findProductByManufacterurId(manufacterurId);
		return pdtoList;
    }

}
