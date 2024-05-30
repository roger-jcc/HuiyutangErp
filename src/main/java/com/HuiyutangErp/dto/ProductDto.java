package com.HuiyutangErp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private Integer id;
	private String manufacturerName;
	private String productName;
	private Double puchasePrice;
	private Double sellingPrice;
	private Integer count;
	private String specifiCation;
	private String category;
	private String storeHouse;
	private String images;
	private Date restockingDate;
	private Date validDate;
}
