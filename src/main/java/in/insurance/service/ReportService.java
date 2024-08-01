package in.insurance.service;

import java.util.List;

import in.insurance.entity.CitizenPlan;
import in.insurance.request.SearchRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	public List<String> getPlanNames();
	public List<String> getPlanStatuses();
	public List<CitizenPlan> search(SearchRequest request);
	public boolean exportExcel(HttpServletResponse servlet,SearchRequest request) throws Exception;
	public boolean exportPdf(HttpServletResponse servlet,SearchRequest request) throws Exception;
}
