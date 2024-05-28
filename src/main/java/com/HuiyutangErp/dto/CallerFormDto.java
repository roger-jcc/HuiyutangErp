package com.HuiyutangErp.dto;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallerFormDto {

	private Integer id;

	/**
	 * 叫貨人
	 */
	private String caller;

	/**
	 * 購物台
	 */
	private String shopping;

	/**
	 * 產品名稱
	 */
	private String productName;

	/**
	 * 叫貨數量
	 */
	private String count;
	
	/*
	 * 叫貨日期
	 */
	private Date callerDate;

}
