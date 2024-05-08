package com.example.HuiYuTang.HuiYuTang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HuiYuTang.HuiYuTang.pojo.Manufacturer;

@Repository
public interface ManufacterurRepository extends JpaRepository<Manufacturer, Integer> {
	
	Optional<Manufacturer> findByManufacturerName(String maunfacturerame);

}
