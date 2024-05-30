package com.HuiyutangErp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HuiyutangErp.pojo.Product;
import com.HuiyutangErp.repository.ManufacterurRepository;
import com.HuiyutangErp.repository.ProductRepository;
import com.HuiyutangErp.service.ShipService;

@RestController
@RequestMapping("/admin/shipdata")
public class ShipDataController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ShipService shipService;
	
	@Autowired
	private ManufacterurRepository manufacterurRepository;
	
	/**
	 * 產品查詢
	 * @param str
	 * @return
	 */
	@GetMapping("/findProduct")
	public Integer findProduct(@RequestParam(name="productName" ,required = true) String str ,Model mod) {
		Optional<Product> product =  productRepository.findByProductName(str);
		Product pro = new Product();
		if(product.isPresent()) {
			pro = product.get();
		}
		
		return pro.getCount();
	}
	
	/**
	 * 廠商查詢 產品列表
	 * @param str
	 * @return
	 */
	@GetMapping("/findProductByManufacturerName")
	public 	 void findProductByManufacturerName(@RequestParam(name="manufacturerName" ,required = true) String str ,Model mod) {
		
		List<String> productNameList =shipService.findProductByManufacturerName(str);
		
		mod.addAttribute("productNameList",productNameList);
	}
	
	
	/**
	 * 儲存出貨訊息單筆
	 * @param req
	 * @throws Exception 
	 */
	@PostMapping("/saveShip") 
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public void saveRestock(
			 @RequestParam(name ="manufacturer") String manufacturer,
            @RequestParam(name ="productName") String productName,
            @RequestParam(name = "count") int count,
            @RequestParam(name ="shipReason") String shipReason , Model mod) throws Exception{
		Map<String,String> resMap = shipService.saveShip(manufacturer, productName, count, shipReason);
		
		if(StringUtils.equals(resMap.get("code"), "success") ) {
			mod.addAttribute("message", resMap.get("message"));
		}else {
			mod.addAttribute("message", resMap.get("message"));
		}
	}
	
	
	
	

}
