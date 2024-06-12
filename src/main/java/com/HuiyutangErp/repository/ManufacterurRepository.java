package com.HuiyutangErp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HuiyutangErp.pojo.Manufacturer;

@Repository
public interface ManufacterurRepository extends JpaRepository<Manufacturer, Integer> {
	
	Optional<Manufacturer> findByManufacturerName(String maunfacturerame);

	List<Manufacturer> findByManufacturerNameLike(String manufacturerName);

}
