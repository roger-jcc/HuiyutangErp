package com.HuiyutangErp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HuiyutangErp.pojo.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Optional<Product> findByProductName(String productName);
	
	
    List<Product> findByProductNameLike(String productName);
    
    List<Product> findByManufacturerId(String manufacturerId);
}
