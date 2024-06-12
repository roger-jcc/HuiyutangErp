package com.HuiyutangErp.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.HuiyutangErp.bean.CallFormReq;
import com.HuiyutangErp.dto.CallerFormDto;
import com.HuiyutangErp.mapper.CallerFormMapper;
import com.HuiyutangErp.pojo.CallerForm;
import com.HuiyutangErp.pojo.Product;
import com.HuiyutangErp.repository.CallerFormRepository;
import com.HuiyutangErp.repository.ProductRepository;

import io.jsonwebtoken.lang.Collections;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class CallerFormService {
	
	@Autowired
	private CallerFormRepository callerformRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	public void saveCallFrom(CallFormReq coallerFormReq) throws ParseException {
		CallerForm caller  = new CallerForm();
		caller.createCallerForm(coallerFormReq);
		callerformRepository.save(caller);
	}



	public void deleteCallerForm(String id) {
		Integer ID = Integer.parseInt(id);
		callerformRepository.deleteById(ID);
	}



	public Page<CallerFormDto> getPageCallerFormDto(Pageable pageable) {
		Page<CallerForm> callerList= callerformRepository.findAll(pageable);
		Page<CallerFormDto> callDtoPage = callerList.map(CallerFormMapper::toDto);
		return callDtoPage;
	}



	public List<String> findByProductNameLike(String str) {
		List<String> productNameList = new ArrayList<>();
		List<Product> productList = productRepository.findByProductNameLike("%"+str+"%");
		if(!Collections.isEmpty(productList)) {
			for(Product pro :productList) {
				productNameList.add(pro.getProductName());
			}
		}
		
		return productNameList;
	}
	
	
	
	

}
