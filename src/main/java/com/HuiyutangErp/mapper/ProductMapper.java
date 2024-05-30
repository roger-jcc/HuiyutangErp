package com.HuiyutangErp.mapper;

import java.util.Base64;

import com.HuiyutangErp.dto.ProductDto;
import com.HuiyutangErp.pojo.Product;

public class ProductMapper {
	
	
	  public static ProductDto toDto(Product product) {
	        if (product == null) {
	            return null;
	        }
	        ProductDto dto = new ProductDto();
	        dto.setId(product.getId());
	        dto.setManufacturerName(product.getManufacturerName());
	        dto.setProductName(product.getProductName());
	        dto.setPuchasePrice(product.getPuchasePrice());
	        dto.setSellingPrice(product.getSellingPrice());
	        dto.setCount(product.getCount());
	        dto.setCategory(product.getCategory());
	        dto.setSpecifiCation(product.getSpecifiCation());
	        dto.setStoreHouse(product.getStoreHouse());
	        if(product.getImages()!=null) {
	        	dto.setImages( Base64.getEncoder().encodeToString(product.getImages()));
	        }
	        dto.setRestockingDate(product.getRestockingDate());
	        dto.setValidDate(product.getValidDate());
	        return dto;
	    }

}
