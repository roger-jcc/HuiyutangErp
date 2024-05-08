package com.example.HuiYuTang.HuiYuTang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HuiYuTang.HuiYuTang.pojo.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Optional<Product> findByProductName(String productName);

}
