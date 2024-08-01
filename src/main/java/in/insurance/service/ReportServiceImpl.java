package in.insurance.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.insurance.entity.CitizenPlan;
import in.insurance.repository.CitizenPlanRepository;
import in.insurance.request.SearchRequest;
import in.insurance.utils.EmailUtils;
import in.insurance.utils.ExcelGenerator;
import in.insurance.utils.PdfGenerator;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private CitizenPlanRepository repo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		if (null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if(null!=request.getPlanStartDate() && !"".equals(request.getPlanStartDate())) {
			String startDate = request.getPlanStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
			
		}
		if(null!=request.getPlanEndDate() && !"".equals(request.getPlanEndDate())) {
			String endDate = request.getPlanEndDate();
			System.out.println(endDate);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			System.out.println(localDate);
			entity.setPlanEndDate(localDate);
			
		}
		return repo.findAll(Example.of(entity));
	}

	public boolean exportExcel(HttpServletResponse response,SearchRequest request) throws Exception {
		File file = new File("plan.xlsx");
		List<CitizenPlan> records = search(request);
		excelGenerator.generate(response, records,file);
		String subject = "Test mail subject";
		String to = "robert24197@gmail.com";
		String body = "<h1>hello</h1>";
		emailUtils.sendEmail(subject, body, to, file);
		file.delete();
		return true;
		
		
	}

	@Override
	public boolean exportPdf(HttpServletResponse response,SearchRequest request) throws Exception {
		File file = new File("plan.pdf");
		List<CitizenPlan> plans = search(request);
		pdfGenerator.generate(response, plans, file);
		String subject = "Test mail subject";
		String to = "robert24197@gmail.com";
		String body = "<h1>hello</h1>";
		
		emailUtils.sendEmail(subject, body, to,file);
		return true;
	}

	@Override
	public List<String> getPlanNames() {
		return repo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return repo.getPlanStatus();
	}
	
}
