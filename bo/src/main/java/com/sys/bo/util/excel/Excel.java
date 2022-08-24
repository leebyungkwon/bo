package com.sys.bo.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


public class Excel<T> {
	public Excel() {
		
	}

	public List<Map<String, Object>> upload(String path) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
		Workbook wb = ExcelFileType.getWorkbook(path);
		Sheet sheet = wb.getSheetAt(0);
		
		int numOfCells =sheet.getRow(0).getPhysicalNumberOfCells();
		for(int i=1; i<sheet.getLastRowNum() + 1; i++) {
		    Row row = null;
		    Cell cell = null;
		    
		    String cellName = "";
		    
		    Map<String, Object> map = null;
	        row = sheet.getRow(i);
	        
	        if(row != null) {
	            map = new HashMap<String, Object>();
	            for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
	                cell = row.getCell(cellIndex);
	                cellName = ExcelCellRef.getName(cell, cellIndex);
	                
	                map.put(cellName, ExcelCellRef.getValue(cell));
	            }
	            result.add(map);
		    }
		}
		return result;
	}
	
	public void downLoad(List<T> datas, Class<T> type,OutputStream stream) throws IOException, IllegalArgumentException, IllegalAccessException {
		
		SXSSFWorkbook wb = new SXSSFWorkbook();
		Sheet sheet = wb.createSheet();
		int rowIndex = 0;
		Row row = sheet.createRow(rowIndex++);
		
		for (Field field : type.getDeclaredFields()) {
			field.setAccessible(true);
			if (null != field.getAnnotation(ExcelColumn.class)) {
				Cell cell = row.createCell(field.getAnnotation(ExcelColumn.class).order());
				cell.setCellValue(field.getAnnotation(ExcelColumn.class).headerName().toString());
			}
		}

		for(T data: datas) {
			row = sheet.createRow(rowIndex++);
			for (Field typefield : type.getDeclaredFields()) {
				for(Field datafield : data.getClass().getDeclaredFields()){
					if (null != typefield.getAnnotation(ExcelColumn.class)) {
						if(datafield.getName().equals(typefield.getName())) {
							datafield.setAccessible(true);
							typefield.setAccessible(true);
							//Object value = typefield.get(data); 
							Object value = datafield.get(data); 
							Cell cell = row.createCell(typefield.getAnnotation(ExcelColumn.class).order());
							
							if(value == null || value.equals("")) {
								cell.setCellValue("");
							}else {
								cell.setCellValue(StringEscapeUtils.unescapeHtml3(value.toString()));
							}
							
							break;
						}
					}
				}
			}
		}
		wb.write(stream);
		wb.close();
		wb.dispose();
		stream.close();
	}

}
