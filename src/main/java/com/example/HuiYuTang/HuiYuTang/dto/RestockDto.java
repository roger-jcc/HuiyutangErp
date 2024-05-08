package com.example.HuiYuTang.HuiYuTang.dto;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestockDto {
	
	/**
	 * 廠商
	 */
	private String manufacturerName;
	
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
	 * 產品照片
	 */
	private String images;
	
	/**
	 * 進貨日
	 */
	private Date restockingDate;
	
	/**
	 * 有效日
	 */
	private Date validDate;

}
