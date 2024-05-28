package com.HuiyutangErp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HuiyutangErp.service.RestockService;


/**
 * 出貨
 * @author hank
 *
 */
@Controller
@RequestMapping("/admin/ship")
public class ShipController {
	
	@Autowired
	private RestockService restockService;
	
	
	@GetMapping("/shipformList")
	public String shipPage(Model mod) {
		return "admin/shipformList";
	}
	
	@GetMapping("/shipform")
	public String shipform(Model mod) {
		return "admin/shipform";
	}
}
