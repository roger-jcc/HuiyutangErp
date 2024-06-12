package com.HuiyutangErp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.HuiyutangErp.dto.ManufacterurDto;
import com.HuiyutangErp.dto.ShipDto;
import com.HuiyutangErp.service.ManufacturerService;
import com.HuiyutangErp.service.ShipService;

import jakarta.servlet.http.HttpSession;


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
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	
	@GetMapping("/shipformList")
	public String shipPage(Model mod,HttpSession session,
			 @RequestParam(defaultValue = "0") int page, 
	            @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<ShipDto> shipdtoPage = shipService.getShipDtoPage(pageable);
		
		mod.addAttribute("shipdtoPage", shipdtoPage);
		return "admin/shipformList";
	}
	
	@GetMapping("/shipform")
	public String shipform(Model mod) {
		List<ManufacterurDto> manufacterurList = manufacturerService.findAllManufacturer();
		mod.addAttribute("manufacterurList", manufacterurList);
		return "admin/shipform";
	}
}
