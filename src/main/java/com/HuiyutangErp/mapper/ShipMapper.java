package com.HuiyutangErp.mapper;

import com.HuiyutangErp.dto.ShipDto;
import com.HuiyutangErp.pojo.Ship;

public class ShipMapper {
	
	public static ShipDto toDto(Ship ship) {
		if (ship == null) {
            return null;
        }
		ShipDto dto = new ShipDto();
		dto.setId(ship.getId());
		dto.setManufacturerName(ship.getManufacturerName());
		dto.setProductName(ship.getProductName());
		dto.setCount(ship.getCount());
		dto.setAmount(ship.getAmount());
		dto.setShippingDate(ship.getShippingDate());
		dto.setShipReason(ship.getShipReason());
	
		
		return dto;
	}

}
