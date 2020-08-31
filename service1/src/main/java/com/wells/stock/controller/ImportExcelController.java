package com.wells.stock.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wells.stock.dao.ExcelRepo;
import com.wells.stock.entity.StockPriceEntity;
import com.wells.stock.entity.Test;
import com.wells.stock.model.ExcelData;


@RestController
public class ImportExcelController {
	
	@Autowired
	ExcelRepo repo;
	
	@RequestMapping("/home")
	public ModelAndView home() {
		//ModelAndView mv=new ModelAndView("file");
		//return mv;
		System.out.println("reached!");
		ModelAndView mv=new ModelAndView("file");
		System.out.println("Home page");
		return mv;
	}
	
	//using apache poi for reading excel files
	
	
	@PostMapping(path="/importExcel")
	public String importExcel(Model model,MultipartFile file) throws IOException
	{
		System.out.println("Reached here");
		String s=file.getOriginalFilename();
		System.out.println(s);
		InputStream in=file.getInputStream();
		File currDir=new File(".");
		String path=currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
	    FileOutputStream f = new FileOutputStream(fileLocation);
	    int ch = 0;
	    while ((ch = in.read()) != -1) {
	        f.write(ch);
	    }
	    f.flush();
	    f.close();
	    System.out.println("File has been added: "+file.getOriginalFilename());;
		FileInputStream input=null;
		Workbook workbook=null;
		int count=0;
		if(s.endsWith(".xlsx"))
		{
			input=new FileInputStream(new File(s));
			workbook=new XSSFWorkbook(input);
		}
		else if(s.endsWith(".xls"))
		{
			input=new FileInputStream(new File(s));
			workbook=new HSSFWorkbook(input);
		}
		else 
		{
			throw new IOException("Not an Excel Sheet!");
		}
		int totalSheets=workbook.getNumberOfSheets();
		for(int i=0;i<totalSheets;i++)
		{
			Sheet sheet=workbook.getSheetAt(i);
			Iterator<Row> iter=sheet.iterator();
			//default row
			if(iter.hasNext()) 
			{
				Row firstRow=iter.next();
				iter.next();
			}
			while(iter.hasNext())
			{
				Row nextRow=iter.next();
				Iterator<Cell> citer=nextRow.cellIterator();
				StockPriceEntity entity=new StockPriceEntity();
				while(citer.hasNext())
				{
					Cell nextCell=citer.next();
					int colIndex=nextCell.getColumnIndex();
					switch(colIndex)
					{
					case 0:
						String cc=nextCell.getStringCellValue().replaceAll("\u00A0", "");
						//replaceAll(“\u00A0”, “”);
						entity.setCompanyCode(Integer.parseInt(cc));
						System.out.println(cc);
						break;
					case 1:
						String a=nextCell.getStringCellValue();
						entity.setStockExchange(a);
						System.out.println(a);
						break;
					case 2:
						//String inputt1=nextCell.getStringCellValue().replaceAll("\u00A0", "");
						entity.setCurrentPrice((float)nextCell.getNumericCellValue());
						break;
					case 3:
						DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd");
						SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
						Date d=nextCell.getDateCellValue();
						String ds=formatter1.format(d);
						System.out.println(ds);
						entity.setDate(LocalDate.parse(ds,format));
						System.out.println(ds);
						break;
					case 4:
						DateTimeFormatter formatter=DateTimeFormatter.ISO_TIME;
						String t=nextCell.getStringCellValue().replaceAll("\u00A0", "").trim();
						System.out.println(t);
						entity.setTime(LocalTime.parse(t,formatter));
						break;
					}
				}
				//System.out.println(entity.toString());
				repo.save(entity);
				//System.out.println(entity.toString());
			}
		}
		workbook.close();
		input.close();
		
		return "a";
	}
}
