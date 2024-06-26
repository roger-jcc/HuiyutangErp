package com.HuiyutangErp.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
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
import org.apache.poi.xssf.usermodel.XSSFPictureData;
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
import com.HuiyutangErp.pojo.Restock;
import com.HuiyutangErp.repository.ManufacterurRepository;
import com.HuiyutangErp.repository.ProductRepository;
import com.HuiyutangErp.repository.RestockRepository;

import io.micrometer.common.util.StringUtils;
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
	public Map<String,String> saveRestock( Restockreq req) throws IOException, SQLException {
		Map<String , String> resMap = new HashMap<>();
		ProductReq proreq = new ProductReq();
		proreq.setManufacturerName(req.getManufacturer());
		proreq.setProductName(req.getProductName());
		proreq.setCount(req.getCount());
		proreq.setPicture(req.getImages().getBytes());
		
		Optional<Manufacturer> manufacterur = manufacterurRepository.findByManufacturerName(req.getManufacturer());
		if(manufacterur.isPresent()) {
			Optional<Product> product = productRepository.findByProductName(req.getProductName());
			if(product.isPresent()) {
				//更新
				Manufacturer m = manufacterur.get();
				Product pro  = product.get();
				pro.update(proreq ,pro );
				productRepository.save(pro);
				
			}else {
				//新增
				Manufacturer m = manufacterur.get();
				Product pro  = new Product();
				pro.create(proreq,m);
				productRepository.save(pro);
			}
			resMap.put("code", "success");
			resMap.put("message", "新增成功");
			return resMap;
		}else {
			resMap.put("code", "error");
			resMap.put("message", "查無此廠商 請先新增");
			return resMap;
		}
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
    @Transactional
	public Map<String , String> saveRestockFile(MultipartFile file) throws IOException {
		Map<String, String> resMap = new HashMap<>();
		
		try (InputStream input = file.getInputStream(); XSSFWorkbook book = new XSSFWorkbook(input);) {
			XSSFSheet sheet = book.getSheetAt(0); // 只讀頁簽1
			Map<Integer ,PictureData > picMap = getPicture(sheet);
			
			
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
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
							 PictureData pictureData = picMap.get(row.getRowNum());
							 if(pictureData!=null) {
								 byte[] data = pictureData.getData();
								 req.setPicture(data);
							 }
						}
						//產品規格
						req.setSpecifiCation(getStringCellValue(row,2));
						//產品名稱
						req.setProductName(getStringCellValue(row,3));
						//進貨數量
						req.setCount( getNumericCellValue(row , 4).intValue() );
						//進貨原因
						req.setRestock_reason("期初");
						
						//進貨紀錄
						Restock rs = new Restock();
						rs.setManufacturerName(getStringCellValue(row,0));
						rs.setProductName(getStringCellValue(row,3));
						rs.setCount( getNumericCellValue(row , 4).intValue());
						rs.setRestockReason("期初");
						rs.setRestockingDate( Timestamp.from(Instant.now()));
						restockRepository.save(rs);
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
							Optional<Product> product = productRepository.findByProductName(getStringCellValue(row,3));
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
							resMap.put("message","第"+ (row.getRowNum()+1) +"筆"+ "查無此廠商 請先新增");
							return resMap;
						}
				}
			
			resMap.put("code", "success");
			resMap.put("message", "匯入成功");
			
		}catch(Exception e) {
			e.printStackTrace();
			if(StringUtils.isNotBlank(resMap.get("message"))) {
				return resMap;
			}
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

		public Map<String, String> saveRestock(MultipartFile file, String manufacturer, String productName, int count,
				String restockReason) throws IOException, SQLException {
			Map<String , String> resMap = new HashMap<>();
			ProductReq proreq = new ProductReq();
			proreq.setManufacturerName(manufacturer);
			proreq.setProductName(productName);
			proreq.setCount(count);
			if(file!=null) {
				proreq.setPicture(file.getBytes());
			}
			
			Optional<Manufacturer> manufacterur = manufacterurRepository.findByManufacturerName(manufacturer);
			if(manufacterur.isPresent()) {
				Optional<Product> product = productRepository.findByProductName(productName);
				if(product.isPresent()) {
					//更新
					Product pro  = product.get();
					pro.update(proreq ,pro );
					productRepository.save(pro);
					
				}else {
					//新增
					Manufacturer m = manufacterur.get();
					Product pro  = new Product();
					pro.create(proreq,m);
					productRepository.save(pro);
				}
				resMap.put("code", "success");
				resMap.put("message", "新增成功");
				return resMap;
			}else {
				resMap.put("code", "error");
				resMap.put("message", "查無此廠商 請先新增");
				return resMap;
			}
		}

	    
	

}
