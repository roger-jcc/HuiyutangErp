package com.HuiyutangErp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.HuiyutangErp.dto.CallerFormDto;
import com.HuiyutangErp.repository.CallerFormRepository;
import com.HuiyutangErp.service.CallerFormService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;


/**
 * 出貨
 * @author hank
 *
 */
@Controller
@RequestMapping("/admin/call")
public class CallController {
	

	
	@Autowired
	private CallerFormRepository callerFormRepository;
	
	@Autowired
	private CallerFormService callerFormService;
	
	
	@GetMapping("/callformList")
	public String shipPage(Model mod,HttpSession session,
			 @RequestParam(defaultValue = "0") int page, 
	            @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		Page<CallerFormDto> calldtoPage = callerFormService.getPageCallerFormDto(pageable);
		
		
		
		mod.addAttribute("calldtoPage", calldtoPage);
		
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