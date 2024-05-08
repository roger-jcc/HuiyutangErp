package com.example.HuiYuTang.HuiYuTang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HuiYuTang.HuiYuTang.pojo.Restock;

@Repository
public interface RestockRepository  extends JpaRepository<Restock, Integer>{

}
