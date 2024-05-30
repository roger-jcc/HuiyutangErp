package com.HuiyutangErp.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipDto {
	
	private Integer id;
	
	/**
	 * 廠商名稱
	 */
	private String manufacturerName;
	
	
	/**
	 * 產品名稱
	 */
	private String productName;
	
	
	/**
	 * 出貨數量
	 */
	private Integer count;
	
	
	/**
	 * 剩餘數量
	 */
	private Integer amount;
	
	/*
	 * 出貨日期
	 */
	private Timestamp shippingDate;
	
	/**
	 * 出貨原因
	 */
	private String shipReason;

	
}
