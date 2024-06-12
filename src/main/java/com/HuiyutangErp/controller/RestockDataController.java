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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.HuiyutangErp.bean.Restockreq;
import com.HuiyutangErp.dto.PageDto;
import com.HuiyutangErp.service.RestockService;

@RestController
@RequestMapping(path ="/admin/restockdata" ,  produces = MediaType.APPLICATION_JSON_VALUE)
public class RestockDataController {
	
	@Autowired
	private RestockService restockService;
	
	
	
	/**
	 * 儲存進貨訊息
	 * @param req
	 * @throws Exception 
	 */
	@PostMapping("/saverestock")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<String> saveRestock(@RequestBody Restockreq req) throws Exception{
		try {
			restockService.saveRestock(req);
		} catch (IOException e) {
			throw new Exception("圖片上傳失敗");
		} catch (SQLException e) {
			throw new Exception("圖片上傳失敗");
		}
		return  ResponseEntity.ok("進貨成功");
	}
	
	
	/**
	 * 儲存進貨訊息
	 * @param req
	 * @throws Exception 
	 *///	@PreAuthorize("hasAuthority('ROLE_USER')")
	@PostMapping("/saverestockfile")
	public Map<String, String> saveRestockFile(@RequestParam ("file") MultipartFile file , Model mod) throws Exception{
		if(file.isEmpty()) {
			mod.addAttribute("error", "檔案不能為空");
			throw new Exception("檔案匯入失敗");
		}
		Map<String, String> resMap = restockService.saveRestockFile(file);
		
		if(StringUtils.equals("success",  resMap.get("code"))) {
			mod.addAttribute("message" ,resMap.get("message") );
		}else {
			mod.addAttribute("message" ,resMap.get("message") );
		}
		return resMap;
	}
	
	
	/**
	 * 儲存進貨訊息單筆
	 * @param req
	 * @throws Exception 
	 */
	@PostMapping("/saveRestock") 
	public void saveRestock(@RequestParam(name = "images" ,required = false) MultipartFile file,
            @RequestParam(name ="manufacturer") String manufacturer,
            @RequestParam(name ="productName") String productName,
            @RequestParam(name = "count") int count,
            @RequestParam(name ="restockReason") String restockReason ,
            @RequestParam(name ="location") String location,
            Model mod) throws Exception{
		Map<String, String> resMap = restockService.saveRestock(file,manufacturer,productName,count,restockReason);
		if(StringUtils.equals("success",  resMap.get("code"))) {
			mod.addAttribute("message" ,resMap.get("message") );
		}else {
			mod.addAttribute("message" ,resMap.get("message") );
		}
	}
	
	
	
	
	
	/**
	 * 查詢所有 進貨庫存
	 * @return
	 */
	@PostMapping("/getrestock")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public PageDto getrestock(@PageableDefault(size =10 ,sort = {"id"},direction = Sort.Direction.DESC)Pageable pag ){
		PageDto resp = restockService.getRestock();
		
		return resp;
	}
	
	
	
	

}
