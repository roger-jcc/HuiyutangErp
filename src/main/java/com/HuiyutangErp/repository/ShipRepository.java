package com.HuiyutangErp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HuiyutangErp.pojo.Ship;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Integer> {
	
	

}
