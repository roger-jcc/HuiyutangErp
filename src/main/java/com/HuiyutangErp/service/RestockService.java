package com.HuiyutangErp.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.HuiyutangErp.bean.ProductReq;
import com.HuiyutangErp.bean.Restockreq;
import com.HuiyutangErp.dto.PageDto;
import com.HuiyutangErp.dto.RestockDto;
import com.HuiyutangErp.pojo.Manufacturer;
import com.HuiyutangErp.pojo.Product;
import com.HuiyutangErp.repository.ManufacterurRepository;
import com.HuiyutangErp.repository.ProductRepository;
import com.HuiyutangErp.repository.RestockRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RestockService {
	
	@Autowired
	private RestockRepository restockRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ManufacterurRepository manufacterurRepository;
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	
	
	
	/**
	 * 儲存進貨訊息
	 * @param req
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void saveRestock( Restockreq req) throws IOException, SQLException {
		
	}
	
	/**
	 * 查詢所有 進貨庫存
	 * @return
	 */
	public PageDto getRestock() {
        Pageable page = PageRequest.of(0, 10);
        Page<Product> products = productRepository.findAll(page);

        List<RestockDto> restockDtos = products.getContent().stream()
                .map(this::convertToRestockDto)
                .collect(Collectors.toList());
        
        Collections.sort(restockDtos, Comparator.comparing(dto -> dto.getManufacturerName()));
        
        PageDto dto  = new PageDto();
        dto.setContent(restockDtos);
        dto.setLast(products.isLast());
        dto.setPageNo(products.getNumber());
        dto.setPageSize(products.getSize());
        dto.setTotalElements(products.getTotalElements());
        dto.setTotalPages(products.getTotalPages());

        return dto; 
    }

    private RestockDto convertToRestockDto(Product product) {
        RestockDto dto = new RestockDto();
        dto.setManufacturerName(product.getManufacturerName());
        dto.setProductName(product.getProductName());
        dto.setCount(product.getCount());
        dto.setRestockingDate(product.getRestockingDate());
        dto.setValidDate(product.getValidDate());
        dto.setStoreHouse(product.getStoreHouse());
        dto.setCategory(product.getCategory());
        dto.setSpecifiCation(product.getSpecifiCation());
        dto.setPuchasePrice(product.getPuchasePrice());
        dto.setSellingPrice(product.getSellingPrice());
        return dto;
    }
	/**
	 * 多筆進貨 儲存
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Map<String , String> saveRestockFile(MultipartFile file) throws IOException {
		Map<String, String> resMap = new HashMap<>();
		
		try (InputStream input = file.getInputStream(); XSSFWorkbook book = new XSSFWorkbook(input);) {
			XSSFSheet sheet = book.getSheetAt(0); // 只讀頁簽1
			int rows = sheet.getPhysicalNumberOfRows(); // 多少行
			Map<Integer ,PictureData > picMap = getPicture(sheet);
			
			for (Row row : sheet) {
				 if (row.getRowNum() < 2) {
		                continue;
		            }
				 if (isRowEmpty(row)) {
		                break;
		            }
		               // 获取单元格的值
					 	ProductReq req = new ProductReq();
					 	//廠商名稱
						req.setManufacturerName(getStringCellValue(row,0));
						//產品圖片
						if(picMap.isEmpty()) {
							req.setPicture(null);
						}else {
							req.setPicture(picMap.get(row.getRowNum()-1).getData());
						}
						//產品規格
						req.setSpecifiCation(getStringCellValue(row,2));
						//產品名稱
						req.setProductName(getStringCellValue(row,3));
						//進貨數量
						req.setCount( getNumericCellValue(row , 4).intValue() );
						//類別
//						req.setCategory(getStringCellValue(row,6));
						//庫位
//						req.setStoreHouse(getStringCellValue(row,7));
						//進貨日
//						req.setRestockingDate(getDateTimeCellValue(row,8) );
						//產品有效日
//						req.setValidDate(getDateTimeCellValue(row,9));
						Optional<Manufacturer> manufacterur = manufacterurRepository.findByManufacturerName(req.getManufacturerName());
						if(manufacterur.isPresent()) {
							Optional<Product> product = productRepository.findByProductName(getStringCellValue(row,1));
							if(product.isPresent()) {
								//更新
								Manufacturer m = manufacterur.get();
								Product pro  = product.get();
								pro.update(req ,pro );
								productRepository.save(pro);
								
							}else {
								//新增
								Manufacturer m = manufacterur.get();
								Product pro  = new Product();
								pro.create(req,m);
								productRepository.save(pro);
							}
						}else {
							resMap.put("code", "error");
							resMap.put("message","第"+ (row.getRowNum()-1) +"筆"+ "查無此廠商 請先新增");
							return resMap;
						}
				}
			
			resMap.put("code", "success");
			resMap.put("message", "匯入成功");
			
		}catch(Exception e) {
			e.printStackTrace();
			resMap.put("code", "error");
			resMap.put("message", "匯入檔案發生錯誤 請重新匯入");
			return resMap;
		}
		return resMap;
	}
	
	
	
		private String getStringCellValue(Row row, int cellIndex) {
	        return row.getCell(cellIndex) != null ? row.getCell(cellIndex).getStringCellValue() : "";
	    }

	    private Double getNumericCellValue(Row row, int cellIndex) {
	    	
	    		return row.getCell(cellIndex) != null ? row.getCell(cellIndex).getNumericCellValue():0.0;
	    	
	    }
	    
	    private Date getDateTimeCellValue(Row row, int cellIndex) {
	    		return row.getCell(cellIndex) != null ?  row.getCell(cellIndex).getDateCellValue():null;
	    }
	    
	    
	    private Map<Integer , PictureData> getPicture (XSSFSheet sheet) {
	    	Map<Integer , PictureData> pictureMap = new HashMap<>();
	    	 for (Object obj : sheet.getDrawingPatriarch().getShapes()) {
	                if (obj instanceof Picture) {
	                    XSSFPicture pic = (XSSFPicture) obj;
	                    XSSFClientAnchor anchor = pic.getClientAnchor();
	                    // 獲取圖片數據
	                    PictureData pictureData = pic.getPictureData();
	                    pictureMap.put(anchor.getRow1(), pictureData);
//	                    System.out.println("row1 :"+ (anchor.getRow1()));
//	                    System.out.println("col1 :" +anchor.getCol1());
//	                    System.out.println("row2 :"+anchor.getRow2());
	                }
	            }
	    	
			return pictureMap;
	    	
	    }

	
	
	
	    private static boolean isRowEmpty(Row row) {
	        if (row == null) {
	            return true;
	        }
	        for (Cell cell : row) {
	            if (cell != null && cell.getCellType() != CellType.BLANK) {
	                return false;
	            }
	        }
	        return true;
	    }

	    
	

}
