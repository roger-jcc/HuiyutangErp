package com.HuiyutangErp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HuiyutangErp.dto.ShipDto;
import com.HuiyutangErp.service.RestockService;
import com.HuiyutangErp.service.ShipService;


/**
 * 出貨
 * @author hank
 *
 */
@Controller
@RequestMapping("/admin/ship")
public class ShipController {

	@Autowired
	private ShipService shipService;
	
	
	@GetMapping("/shipformList")
	public String shipPage(Model mod) {
		List<ShipDto> shipDtoList = shipService.findAll();
		
		mod.addAttribute("shipdtoList", shipDtoList);
		return "admin/shipformList";
	}
	
	@GetMapping("/shipform")
	public String shipform(Model mod) {
		return "admin/shipform";
	}
}
