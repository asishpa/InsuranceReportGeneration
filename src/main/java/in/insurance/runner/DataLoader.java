package in.insurance.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.insurance.entity.CitizenPlan;
import in.insurance.repository.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner{
	@Autowired
	private CitizenPlanRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		repo.deleteAll();
		
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizen_name("Rakesh");
		c1.setGender("male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmt(25000.0);
		
		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizen_name("Prakash");
		c2.setGender("male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setDenialReason("Rental Income");
		
		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizen_name("Sneha");
		c3.setGender("Female");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setTerminateDate(LocalDate.now());
		c3.setTerminateReason("Employed");
		c3.setBenefitAmt(500.0);
	
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizen_name("Kashyap");
		c4.setGender("male");
		c4.setPlanName("Food");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(4));
		c4.setBenefitAmt(14000.0);

		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizen_name("Akash");
		c5.setGender("male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denied");
		c5.setDenialReason("Invalid Documents");
		
		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizen_name("Barsha");
		c6.setGender("Female");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(4));
		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
		c6.setTerminateDate(LocalDate.now());
		c6.setTerminateReason("Forged Documents");
		c6.setBenefitAmt(50000.0);
		
		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizen_name("Megha");
		c7.setGender("Female");
		c7.setPlanName("Medical");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(5));
		c7.setBenefitAmt(45000.0);
		
		CitizenPlan c8 = new CitizenPlan();
		c8.setCitizen_name("Kailash");
		c8.setGender("Male");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denied");
		c8.setDenialReason("Criteria not matched");
		
		CitizenPlan c9 = new CitizenPlan();
		c9.setCitizen_name("Kishore");
		c9.setGender("Male");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
		c9.setPlanStartDate(LocalDate.now().minusMonths(1));
		c9.setPlanEndDate(LocalDate.now().plusMonths(6));
		c9.setTerminateDate(LocalDate.now());
		c9.setTerminateReason("Non-payment of premium");
		
		CitizenPlan c10 = new CitizenPlan();
		c10.setCitizen_name("Madhab");
		c10.setGender("Male");
		c10.setPlanName("Employment");
		c10.setPlanStatus("Approved");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(8));
		c10.setBenefitAmt(85000.0);
		
		CitizenPlan c11 = new CitizenPlan();
		c11.setCitizen_name("Subhendu");
		c11.setGender("Male");
		c11.setPlanName("Employment");
		c11.setPlanStatus("Denied");
		c11.setDenialReason("Documents Missing");

		CitizenPlan c12 = new CitizenPlan();
		c12.setCitizen_name("Akanshya");
		c12.setGender("Female");
		c12.setPlanName("Employment");
		c12.setPlanStatus("Terminated");
		c12.setPlanStartDate(LocalDate.now().minusMonths(2));
		c12.setPlanEndDate(LocalDate.now().plusMonths(5));
		c12.setTerminateDate(LocalDate.now());
		c12.setTerminateReason("Misconduct");
		
		List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		repo.saveAll(list);
	}

}
