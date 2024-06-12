package com.HuiyutangErp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HuiyutangErp.service.ManufacturerService;

@RestController
@RequestMapping("/admin/manufacterurdate")
public class ManufacterurDataController {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	
	@GetMapping("/findmanufacturerName")
	public List<String> findmanufacturerName(@RequestParam(name = "manufacturerName", required = false) String manufacturerName ){
		List<String> manufacturerNameList = manufacturerService.findmanufacturerName(manufacturerName);
		return manufacturerNameList;
	}
	

}
