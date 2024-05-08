package com.example.HuiYuTang.HuiYuTang.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HuiYuTang.HuiYuTang.bean.ManufacturerReq;
import com.example.HuiYuTang.HuiYuTang.service.ManufacturerService;

@RestController
@RequestMapping("/api/manufacterur")
public class ManufacterueController {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	/**
	 * 新增廠商資訊
	 * @param manufacteruerReq
	 * @throws Exception 
	 */
	@PostMapping("/saveManufacterue")
	public ResponseEntity<String> saveManufacterue(@RequestBody ManufacturerReq  manufacturerReq ,Model mod) throws Exception  {
		if(StringUtils.isEmpty(manufacturerReq.getManufacturerName())) {
			mod.addAttribute("error" , "廠商名稱不可為空");
			throw new Exception("廠商名稱不可為空");
		}
		
		manufacturerService.saveManufacterue(manufacturerReq);
		mod.addAttribute("sueccess", "新增廠商成功");
		return ResponseEntity.ok("新增廠商成功");
	}

}
