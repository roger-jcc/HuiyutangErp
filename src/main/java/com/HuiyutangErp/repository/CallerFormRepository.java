package com.HuiyutangErp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HuiyutangErp.pojo.CallerForm;

@Repository
public interface CallerFormRepository extends JpaRepository<CallerForm, Integer> {

}
