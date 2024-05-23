package com.HuiyutangErp.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HuiyutangErp.bean.ManufacturerReq;
import com.HuiyutangErp.service.ManufacturerService;

@Controller
@RequestMapping("/admin/manufacterur")
public class ManufacterurController {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	
	@GetMapping("/maunfacterurPage")
	public String vendorlist(Model mod) {
		return "admin/manufacterur";
	}
	
	/**
	 * 新增廠商資訊
	 * @param manufacteruerReq
	 * @throws Exception 
	 */
	@PostMapping("/saveManufacturer")
	public ResponseEntity<String> saveManufacturer(@RequestBody ManufacturerReq  manufacturerReq ,Model mod) throws Exception  {
		if(StringUtils.isEmpty(manufacturerReq.getManufacturerName())) {
			mod.addAttribute("error" , "廠商名稱不可為空");
			throw new Exception("廠商名稱不可為空");
		}
		
		manufacturerService.saveManufacturer(manufacturerReq);
		mod.addAttribute("sueccess", "新增廠商成功");
		return ResponseEntity.ok("新增廠商成功");
	}

}
