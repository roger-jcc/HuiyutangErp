package com.HuiyutangErp.pojo;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import org.springframework.util.ObjectUtils;

import com.HuiyutangErp.bean.ProductReq;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Integer id;
	
	/*
	 * 廠商名稱
	 */
	@Column(name = "MANUFACTURER_NAME")
	private String manufacturerName;
	
	@Transient
	@Column(name = "MANUFACTURER_ID")
	private String manufacturerId;
	
	/**
	 * 品名
	 */
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	/**
	 * 進價
	 */
	@Column(name = "PURCHASE_PRICE")
	private Double puchasePrice;
	
	
	/**
	 * 售價
	 */
	@Column(name = "SELLING_PRICE")
	private Double sellingPrice;
	
	
	/**
	 * 數量
	 */
	@Column(name = "COUNT" )
	private Integer count;
	
	/**
	 * 規格
	 */
	@Column(name = "SPECIFICATION")
	private String specifiCation;
	
	/**
	 * 類別
	 */
	@Column(name = "CATEGORY")
	private String category;
	
	/**
	 * 庫位
	 */
	@Column(name = "STOREHOUSE")
	private String storeHouse;
	
	/**
	 * 產品照片
	 */
	@Lob
	@Column(name = "IMAGES")
	private byte[] images;
	
	/**
	 * 進貨日
	 */
	@Column(name = "RESTOCKING_DATE")
	private Date restockingDate;
	
	/**
	 * 有效日
	 */
	@Column(name = "VALID_DATE")
	private Date validDate;
	
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="manufacturer_id", referencedColumnName="ID")
	private Manufacturer manufacturer;
	
	
	// 新增商品
	@SuppressWarnings("null")
	public void create(ProductReq req, Manufacturer m ) throws IOException, SQLException {
		this.manufacturerName = req.getManufacturerName();
		this.manufacturerId =  String.valueOf(m.getId());
		this.productName = req.getProductName();
		this.puchasePrice = req.getPuchasePrice();
		this.sellingPrice = req.getSellingPrice();
		this.count = req.getCount();
		this.specifiCation = req.getSpecifiCation();
		this.category = req.getCategory();
		this.storeHouse = req.getStoreHouse();
		//圖片
		byte[] imag = req.getPicture();
		this.images = imag;
		
		if(ObjectUtils.isEmpty(req.getRestockingDate())) {
			//進貨日 預設當日
			this.restockingDate = Timestamp.from(Instant.now());
		}else {
			this.restockingDate = req.getRestockingDate();
		}
		this.validDate = req.getValidDate();
	}
	
	
	// 新增商品
		@SuppressWarnings("null")
		public void update(ProductReq req ,Product pro) throws IOException, SQLException {
			 if (req.getManufacturerName() != null) {
			        this.manufacturerName = req.getManufacturerName();
			    }
			    if (req.getProductName() != null) {
			        this.productName = req.getProductName();
			    }
			    if (req.getCount() != null) {  // 修改为 Integer 类型的 count
			        this.count = req.getCount() + pro.getCount();
			    }
			    if (req.getPuchasePrice() != null) {
			        this.puchasePrice = req.getPuchasePrice();
			    }
			    if (req.getSellingPrice() != null) {
			        this.sellingPrice = req.getSellingPrice();
			    }
			    if (req.getSpecifiCation() != null) {
			        this.specifiCation = req.getSpecifiCation();
			    }
			    if (req.getCategory() != null) {
			        this.category = req.getCategory();
			    }
			    if (req.getStoreHouse() != null) {
			        this.storeHouse = req.getStoreHouse();
			    }
			    // 图片
			    if (req.getPicture() != null) {
			        this.images = req.getPicture();
			    }
			    if (req.getValidDate() != null) {
			        this.validDate = req.getValidDate();
			    }
		}
	
}
