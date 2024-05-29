package com.HuiyutangErp.pojo;

import java.sql.Timestamp;
import java.time.Instant;

import com.HuiyutangErp.bean.ProductReq;

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
	 * 出貨數量
	 */
	@Column(name = "COUNT" )
	private Integer count;
	
	/*
	 * 剩餘數量
	 */
	@Column(name = "AMOUNT" )
	private Integer amount;
	
	
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
	
	@Column(name="PRODUCT_ID")
	private String productId;
	
	
	public void create(ProductReq req ,Product pro) {
		this.manufacturerName = req.getManufacturerName();
		this.productName=req.getProductName();
		this.shippingDate = Timestamp.from(Instant.now());
		this.count = req.getCount();//出貨數量
		this.amount = pro.getCount();//目前庫存量
		this.productId = pro.getId().toString();
	}
	

}
