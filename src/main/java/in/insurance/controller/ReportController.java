package in.insurance.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Corrected import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.insurance.entity.CitizenPlan;
import in.insurance.request.SearchRequest;
import in.insurance.service.ReportService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {
    @Autowired
    private ReportService service;
    
    @PostMapping("/excel")
    public void excelExport(@ModelAttribute("search") SearchRequest request,HttpServletResponse response) throws Exception{
    	response.setContentType("application/octet-stream");
    	response.addHeader("Content-Disposition", "attachment;filename=plans.xlsx");
    	service.exportExcel(response,request);
    }
    
    @PostMapping("/pdf")
    public void pdfExport(@ModelAttribute("search") SearchRequest request,HttpServletResponse response) throws Exception{
    	response.setContentType("application/pdf");
    	response.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
    	service.exportPdf(response,request);
    }
    
    @PostMapping("/search")
    public String handleSearch(@ModelAttribute("search") SearchRequest request,Model model) {
    	List<CitizenPlan> plans = service.search(request);
    	model.addAttribute("plans", plans);
    	init(model);
    	return "home";
    }
    @GetMapping("/")
    public String indexpage(Model model) {
    	model.addAttribute("search", new SearchRequest());
        init(model);
        return "home";
    }

	private void init(Model model) {
        //model.addAttribute("search", new SearchRequest());
		model.addAttribute("names", service.getPlanNames());
        model.addAttribute("status", service.getPlanStatuses());
	}
    
}
