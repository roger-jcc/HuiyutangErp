package com.HuiyutangErp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.HuiyutangErp.bean.ManufacturerReq;
import com.HuiyutangErp.pojo.Manufacturer;
import com.HuiyutangErp.repository.ManufacterurRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class ManufacturerService {
	
	@Autowired
	private ManufacterurRepository manufacterurRepository;

	public void saveManufacturer(ManufacturerReq manufacteruerReq) {
		Optional<Manufacturer> manufacteruer = manufacterurRepository.findByManufacturerName(manufacteruerReq.getManufacturerName());
		
		//不存在 則新增
		if(!manufacteruer.isPresent()) {
			Manufacturer mf = new Manufacturer();
			mf.createorupdate(manufacteruerReq);
			manufacterurRepository.save(mf);
		}else {
			//存在 	更新
			Manufacturer existingManufacturer = manufacteruer.get();
		    existingManufacturer.createorupdate(manufacteruerReq); // Update method should be implemented in Manufacturer class
		    manufacterurRepository.save(existingManufacturer);
		}
		
	}
	
	
	

}
