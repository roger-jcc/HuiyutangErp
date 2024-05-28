package com.HuiyutangErp.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.HuiyutangErp.bean.CallFormReq;
import com.HuiyutangErp.pojo.CallerForm;
import com.HuiyutangErp.repository.CallerFormRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class CallerFormService {
	
	@Autowired
	private CallerFormRepository callerformRepository;
	
	
	
	public void saveCallFrom(CallFormReq coallerFormReq) throws ParseException {
		CallerForm caller  = new CallerForm();
		caller.createCallerForm(coallerFormReq);
		callerformRepository.save(caller);
	}



	public void deleteCallerForm(String id) {
		Integer ID = Integer.parseInt(id);
		callerformRepository.deleteById(ID);
	}
	
	
	
	

}
