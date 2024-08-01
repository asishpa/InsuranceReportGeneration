package in.insurance.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.insurance.entity.CitizenPlan;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {

	public void generate(HttpServletResponse response, List<CitizenPlan> plans,File f) throws Exception {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();

		// font style
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setStyle(20);

		Paragraph paragraph = new Paragraph("Citizen Plans Info");
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(6);
		table.addCell("Id");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Plan Start Date");
		table.addCell("Plan end date");

		for (CitizenPlan plan : plans) {
			table.addCell(String.valueOf(plan.getCitizen_id()));
			table.addCell(plan.getCitizen_name());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getPlanStartDate() + "");
			table.addCell(plan.getPlanEndDate() + "");

		}

		document.add(table);
		document.close();
		
	}
}