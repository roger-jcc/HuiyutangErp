package com.HuiyutangErp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.HuiyutangErp.pojo.Product;
import com.HuiyutangErp.repository.ProductRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class ProductService {
	
	
	@Autowired
	private ProductRepository productRepository;




	public Page<Product> findAllProduct(Pageable pageable) {
		
		Page<Product> pageProduct = productRepository.findAll(pageable);
		return pageProduct;
	}
}
