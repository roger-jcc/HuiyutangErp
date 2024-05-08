package com.example.HuiYuTang.HuiYuTang.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
	
	
	private List<RestockDto> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private long totalPages;
	private boolean last;

}
