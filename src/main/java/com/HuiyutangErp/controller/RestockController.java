package com.HuiyutangErp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.HuiyutangErp.bean.Restockreq;
import com.HuiyutangErp.dto.PageDto;
import com.HuiyutangErp.service.RestockService;

/**
 * 進貨
 * @author hank
 *
 */
@Controller
@RequestMapping(path ="/admin/restock" ,  produces = MediaType.APPLICATION_JSON_VALUE)
@EnableMethodSecurity
public class RestockController {
	
	@Autowired
	private RestockService restockService;
	
	
	@GetMapping("/addVendorData")
	public String vendorList() {
		return "admin/addVendorData";
	}
	
	@GetMapping("/addVendorOne")
	public String vendorOne(@RequestParam(name = "caller" ,required = false) String caller
			, @RequestParam(name ="productName",required = false) String productName ,Model mod) {
		mod.addAttribute("caller", caller);
		mod.addAttribute("productName", productName);
		
		return "admin/addVendorOne";
	}
	
	


}
