package com.HuiyutangErp.bean;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Restockreq {
	
	/**
	 * 廠商
	 */
	private String manufacturer;
	
	/**
	 * 品名
	 */
	private String productName;
	
	/**
	 * 數量
	 */
	private Integer count;
	
	
	/**
	 * 產品照片
	 */
	private MultipartFile images;
	
	/**
	 * 進貨日
	 */
	private Timestamp restockingDate;
	
	/**
	 * 有效日
	 */
	private Timestamp validDate;
	
	/*
	 * 進貨原因
	 */
	private String restockReason;
	
	

}
