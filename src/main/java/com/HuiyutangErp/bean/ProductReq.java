package com.HuiyutangErp.bean;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductReq {
	
	/*
	 * 廠商名稱
	 */
	private String manufacturerName;
	
	/**
	 * 產品圖片
	 */
	private byte[] picture;
	/**
	 * 品名
	 */
	private String productName;
	
	/**
	 * 進價
	 */
	private Double puchasePrice;
	
	
	/**
	 * 售價
	 */
	private Double sellingPrice;
	
	
	/**
	 * 數量
	 */
	private Integer count;
	
	/**
	 * 規格
	 */
	private String specifiCation;
	
	/**
	 * 類別
	 */
	private String category;
	
	/**
	 * 庫位
	 */
	private String storeHouse;
	
	
	/**
	 * 進貨日
	 */
	private Date restockingDate;
	
	/*
	 * 進貨原因
	 */
	private String restock_reason;
	
	/**
	 * 有效日
	 */
	private Date validDate;
	

}
