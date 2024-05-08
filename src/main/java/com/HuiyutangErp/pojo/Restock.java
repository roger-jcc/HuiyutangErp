package com.HuiyutangErp.pojo;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 進貨紀錄
 * @author hank
 *
 */
@Data
@Entity
@Table(name = "Restock")
public class Restock {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Integer id;
	
	/**
	 * 廠商名稱
	 */
	@Column(name = "MANUFACTURER_NAME")
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
	 * 進貨價格
	 */
	@Column(name = "RESTOCKING_PRICE")
	private String restockingPrice;
	
	
	
	/**
	 * 進貨日
	 */
	@Column(name = "RESTOCKING_DATE")
	private Timestamp restockingDate;

}
