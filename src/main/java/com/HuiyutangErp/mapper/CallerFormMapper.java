package com.HuiyutangErp.mapper;

import com.HuiyutangErp.dto.CallerFormDto;
import com.HuiyutangErp.pojo.CallerForm;

public class CallerFormMapper {
	
	
	
	public static CallerFormDto toDto(CallerForm call) {
		if (call == null) {
            return null;
        }
		CallerFormDto dto = new CallerFormDto();
		dto.setId(call.getId());
		dto.setCaller(call.getCaller());
		dto.setCount(call.getCount());
		dto.setShopping(call.getShopping());
		dto.setProductName(call.getProductName());
		dto.setCallerDate(call.getCallerDate());
		
		return dto;
	}

}
