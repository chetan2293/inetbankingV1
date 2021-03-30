package com.inetbanking.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	static FileInputStream fis;
	static XSSFWorkbook wb;
	static XSSFSheet ws;
	static XSSFRow row;
	static XSSFCell cell;
	static FileOutputStream fos;
	
	public static int getRowCount(String xlFile,String xlsheet) throws IOException{
		
		fis = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fis.close();
		return rowcount;
	}
	
	public static int getCellCount(String xlFile,String xlsheet,int rownum) throws IOException{
		
		fis = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int CellCount = row.getLastCellNum();
		wb.close();
		fis.close();
		return CellCount;
	}
	
	public static String getCellData(String xlFile,String xlsheet,int rownum,int colnum) throws IOException{
		
		fis = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		
		try{
			
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}
		catch (Exception e){
			
			data = "";
		}
		wb.close();
		fis.close();
		return data;
	}
	
	public static void setCellData(String xlFile,String xlsheet,int rownum,int colnum, String data) throws IOException{
		
		fis = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(xlFile);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}
}
