package in.insurance.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.insurance.entity.CitizenPlan;
import in.insurance.repository.CitizenPlanRepository;
import in.insurance.request.SearchRequest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
	
	
	public boolean generate(HttpServletResponse response,List<CitizenPlan> records,File file) throws Exception {
		Workbook workbok = new XSSFWorkbook();
		Sheet sheet = workbok.createSheet("plans-data");
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Id");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amt");
		
		CellStyle dateCellStyle = workbok.createCellStyle();
	    dateCellStyle.setDataFormat(workbok.createDataFormat().getFormat("yyyy-mm-dd"));
		
		int dataRowIndex = 1;
		for (CitizenPlan plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizen_id());
			dataRow.createCell(1).setCellValue(plan.getCitizen_name());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			if(null!=plan.getPlanStartDate()) {
				Cell startDateCell = dataRow.createCell(4);
	            startDateCell.setCellValue(plan.getPlanStartDate());
	            startDateCell.setCellStyle(dateCellStyle);
			}
			else {
				dataRow.createCell(4).setCellValue("N/A");
			}
			if (null!=plan.getPlanEndDate()) {
				Cell endDateCell = dataRow.createCell(5);
	            endDateCell.setCellValue(plan.getPlanEndDate());
	            endDateCell.setCellStyle(dateCellStyle);
				dataRow.createCell(5).setCellValue(plan.getPlanEndDate());
			}
			else {
				dataRow.createCell(5).setCellValue("null");
			}
	        
			if (null!=plan.getBenefitAmt()) {
				
				dataRow.createCell(6).setCellValue(plan.getBenefitAmt());
			}
			else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			
			dataRowIndex++;
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		workbok.write(fos);
		fos.close();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbok.write(outputStream);
		workbok.close();
		return true;
	}
	
	

}
