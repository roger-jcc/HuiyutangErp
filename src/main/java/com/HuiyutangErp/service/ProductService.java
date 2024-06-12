package com.HuiyutangErp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.HuiyutangErp.dto.ProductDto;
import com.HuiyutangErp.mapper.ProductMapper;
import com.HuiyutangErp.pojo.Product;
import com.HuiyutangErp.repository.ProductRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class ProductService {
	
	
	@Autowired
	private ProductRepository productRepository;




	public Page<ProductDto> findAllProduct(Pageable pageable) {
		
		Page<Product> pageProduct = productRepository.findAll(pageable);
		Page<ProductDto> pdto =	pageProduct.map(ProductMapper::toDto);
		
		return pdto;
	}




	 public  List<ProductDto> findProductByManufacterurId(Long manufacterurId) {
		  List<Product> proList = productRepository.findByManufacturerId(String.valueOf(manufacterurId));
		  List<ProductDto> pdtoList = proList.stream().map(ProductMapper::toDto).collect(Collectors.toList());
		return pdtoList;
	}
}
