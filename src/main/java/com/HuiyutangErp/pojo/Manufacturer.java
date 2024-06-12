package com.HuiyutangErp.pojo;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.HuiyutangErp.bean.ManufacturerReq;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 廠商名稱
 * @author hank
 *
 */
@Data
@Entity
@Table(name = "Manufacturer")
public class Manufacturer {
	
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
	 * 廠商名稱
	 */
	@Column(name = "MANUFACTURER_ADDRESS")
	private String manufacturerAdress;
	
	
	
	/**
	 * 廠商電話
	 */
	@Column(name = "MANUFACTURER_PHONE")
	private String manufacturerPhone;
	
	
	
	/**
	 * 廠商代碼
	 */
	@Column(name = "MANUFACTURER_CODE")
	private String manufacturerCode;
	
	
	/**
	 * 特殊備註
	 */
	@Column(name = "MANUFACTURER_REMARK")
	private String manufacturerRemark;
	
	
	@JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
    @EqualsAndHashCode.Exclude
    private Set<Product> products;
	


	public void createorupdate(ManufacturerReq manufacteruerReq) {
		if(StringUtils.isNotBlank(manufacteruerReq.getManufacturerName())) {
			this.manufacturerName = manufacteruerReq.getManufacturerName();
		}
		
		if(StringUtils.isNotBlank(manufacteruerReq.getManufacturerAdress())) {
			this.manufacturerAdress = manufacteruerReq.getManufacturerAdress();
		}
		
		if(StringUtils.isNotBlank(manufacteruerReq.getManufacturerPhone())) {
			this.manufacturerPhone = manufacteruerReq.getManufacturerPhone();
		}
		
		if(StringUtils.isNotBlank(manufacteruerReq.getManufacturerCode())) {
			this.manufacturerCode = manufacteruerReq.getManufacturerCode();
		}
		
		if(StringUtils.isNotBlank(manufacteruerReq.getManufacturerRemark())) {
			this.manufacturerRemark = manufacteruerReq.getManufacturerRemark();
		}
		
	}

}
