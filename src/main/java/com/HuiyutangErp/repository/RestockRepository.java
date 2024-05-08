package com.HuiyutangErp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HuiyutangErp.pojo.Restock;

@Repository
public interface RestockRepository  extends JpaRepository<Restock, Integer>{

}
