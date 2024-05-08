package com.HuiyutangErp.pojo;

import java.util.Set;

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
	 * 廠商名稱
	 */
	@Column(name = "MANUFACTURER_Phone")
	private String manufacturerPhone;
	
	
	@JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
    @EqualsAndHashCode.Exclude
    private Set<Product> products;
	


	public void createorupdate(ManufacturerReq manufacteruerReq) {
		this.manufacturerName = manufacteruerReq.getManufacturerName();
		this.manufacturerAdress = manufacteruerReq.getManufacturerAdress();
		this.manufacturerPhone = manufacteruerReq.getManufacturerPhone();
	}

}
