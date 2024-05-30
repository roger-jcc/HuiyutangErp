package com.HuiyutangErp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.HuiyutangErp.bean.ProductReq;
import com.HuiyutangErp.dto.ShipDto;
import com.HuiyutangErp.pojo.Manufacturer;
import com.HuiyutangErp.pojo.Product;
import com.HuiyutangErp.pojo.Ship;
import com.HuiyutangErp.repository.ManufacterurRepository;
import com.HuiyutangErp.repository.ProductRepository;
import com.HuiyutangErp.repository.ShipRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class ShipService {
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ShipRepository shipRepository;

	@Autowired
	private ManufacterurRepository manufacterurRepository;
	
	
	
	public Map<String, String> saveShip(String manufacturer, String productName, int count,
			String shipReason) throws Exception {
		Map<String , String> resMap = new HashMap<>();
		ProductReq proreq = new ProductReq();
		proreq.setManufacturerName(manufacturer);
		proreq.setProductName(productName);
		proreq.setCount(count);
		proreq.setShipReason(shipReason);
		
			Optional<Product> product = productRepository.findByProductName(productName);
			if(product.isPresent()) {
				//出貨
				Product pro  = product.get();
				pro.ship(proreq ,pro );
				productRepository.save(pro);
				
				//紀錄出貨
				Ship ship =new Ship();
				ship.create(proreq,pro);
				shipRepository.save(ship);
				
				
			}else {
				resMap.put("code", "error");
				resMap.put("message","找不到此產品" );
			}
			resMap.put("code", "success");
			resMap.put("message", "出貨成功");
			return resMap;
		
	}



	/**
	 * 一廠商 找產品列表
	 * @param str
	 * @return
	 */
	public List<String> findProductByManufacturerName(String str) {
		Optional<Manufacturer> man =  manufacterurRepository.findByManufacturerName(str);
		List<Product> prolist = new ArrayList<>();
		List<String> productNameList = new ArrayList<>();
		
		if(man.isPresent()) {
			prolist=productRepository.findByManufacturerId(man.get().getId().toString());
			productNameList = prolist.stream().map(Product::getProductName).collect(Collectors.toList());
		}
		
		return productNameList;
	}




	public List<ShipDto> findAll() {
		List<Ship> shipList = shipRepository.findAll();
		 List<ShipDto> shipdtoList = shipList.stream()
		.map(ship -> new ShipDto(
				 ship.getId(),
				 ship.getManufacturerName(),
				 ship.getProductName(),
				 ship.getCount(),
				 ship.getAmount(),
				 ship.getShippingDate(),
				 ship.getShipReason()
				 )).collect(Collectors.toList());
		return shipdtoList;
	}
	
	

}
