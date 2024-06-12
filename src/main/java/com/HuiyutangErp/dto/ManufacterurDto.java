package com.HuiyutangErp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacterurDto {
	
	
	  private Integer id;
	  private String manufacturerName;
	  private String manufacturerAdress;
	  private String manufacturerPhone;
	  private String manufacturerCode;
	  private String manufacturerRemark;
	  
}
