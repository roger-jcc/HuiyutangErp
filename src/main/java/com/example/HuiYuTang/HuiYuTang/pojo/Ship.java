package com.example.HuiYuTang.HuiYuTang.pojo;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


/**
 * 出貨紀錄
 * @author hank
 *
 */

@Data
@Entity
@Table(name ="Ship")
public class Ship {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Integer id;
	
	
	/**
	 * 廠商
	 */
	@Column(name ="MANUFACTURER_NAME")
	private String manufacturerName;
	
	/**
	 * 品名
	 */
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	
	/**
	 * 數量
	 */
	@Column(name = "COUNT" )
	private Integer count;
	
	
	/**
	 * 出貨日
	 */
	@Column(name = "SELLING_PRICE")
	private String sellingPrice;
	

	/**
	 * 出貨日
	 */
	@Column(name = "SHIPPING_DATE")
	private Timestamp shippingDate;
	
	
	
	/**
	 * 有效日
	 */
	@Column(name = "VALID_DATE")
	private Timestamp validDate;
	
	
	

}
