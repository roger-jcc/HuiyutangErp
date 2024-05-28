package com.HuiyutangErp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HuiyutangErp.bean.CallFormReq;
import com.HuiyutangErp.pojo.Product;
import com.HuiyutangErp.repository.ProductRepository;
import com.HuiyutangErp.service.CallerFormService;

import io.jsonwebtoken.lang.Collections;
import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/admin/calldata")
public class CallDataController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CallerFormService callerFormService;
	
	
	/**
	 * 產品模糊查詢
	 * @param str
	 * @return
	 */
	@GetMapping("/findProductName")
	public List<String> findProductName(@RequestParam(name="productName" ,required = true) String str) {
		List<String> productNameList = new ArrayList<>();
		List<Product> productList =  productRepository.findByProductNameLike("%"+str+"%");
		
		if(!Collections.isEmpty(productList)) {
			for(Product pro :productList) {
				productNameList.add(pro.getProductName());
			}
		}
		
		return productNameList;
	}
	
	/**
	 * 新增叫貨紀錄
	 * @param manufacteruerReq
	 * @throws Exception 
	 */
	@PostMapping("/saveCallForm")
	public void saveCallFrom(@RequestBody CallFormReq  callformreq ,Model mod) throws Exception  {
		if(callformreq!=null) {
			callerFormService.saveCallFrom(callformreq);
			mod.addAttribute("sueccess", "新增叫貨成功");
		}else {
			mod.addAttribute("error", "新增叫貨失敗");
		}
		
	}
	
	

}
