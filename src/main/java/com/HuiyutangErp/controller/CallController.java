package com.HuiyutangErp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HuiyutangErp.dto.CallerFormDto;
import com.HuiyutangErp.pojo.CallerForm;
import com.HuiyutangErp.repository.CallerFormRepository;
import com.HuiyutangErp.repository.ProductRepository;
import com.HuiyutangErp.service.CallerFormService;

import io.micrometer.common.util.StringUtils;


/**
 * 出貨
 * @author hank
 *
 */
@Controller
@RequestMapping("/admin/call")
public class CallController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CallerFormRepository callerFormRepository;
	
	@Autowired
	private CallerFormService callerFormService;
	
	
	@GetMapping("/callformList")
	public String shipPage(Model mod) {
		
		List<CallerForm> callerList= callerFormRepository.findAll();
		
		List<CallerFormDto> callerdtoList  = callerList.stream()
	            .map(caller -> new CallerFormDto(
	                    caller.getId(),
	                    caller.getCaller(),
	                    caller.getShopping(),
	                    caller.getProductName(),
	                    caller.getCount(),
	                    caller.getCallerDate()
	                ))
	                .collect(Collectors.toList());
		
		mod.addAttribute("callerdtoList", callerdtoList);
		
		return "admin/callformList";
	}
	
	@GetMapping("/callform")
	public String form(Model mod) {
		return "admin/callform";
	}
	
	@GetMapping("/{id}")
	public String deleteCallerForm(@PathVariable("id") String id,Model mod) {
		if(StringUtils.isBlank(id)) {
			mod.addAttribute("error", "刪除失敗");
		}
		callerFormService.deleteCallerForm(id);
		
		return "redirect:/admin/call/callformList";
	}

	
}