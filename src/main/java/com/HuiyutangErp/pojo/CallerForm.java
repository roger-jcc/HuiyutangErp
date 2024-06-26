package com.HuiyutangErp.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.HuiyutangErp.bean.CallFormReq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Callform")
public class CallerForm {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Integer id;
	
	/**
	 * 叫貨人
	 */
	@Column(name="CALLER")
	private String caller;
	
	/**
	 * 購物台
	 */
	@Column(name="SHOPPING")
	private String shopping;
	
	/**
	 * 產品名稱
	 */
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	/**
	 * 叫貨數量
	 */
	@Column(name="COUNT")
	private String count;
	
	
	/**
	 * 叫貨數量
	 */
	@Column(name="CALLER_DATE")
	private Date callerDate;
	
	
	public void createCallerForm(CallFormReq req) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		String calldate = ft.format(new Date());
		Date callerDate = ft.parse(calldate);
		this.caller = req.getCaller();
		this.shopping = req.getShopping();
		this.productName = req.getProductName();
		this.count =req.getCount();
		this.callerDate =callerDate ;
	}

}
