package in.insurance.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class CitizenPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long citizen_id;
	private String citizen_name;
	private String gender;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benefitAmt;
	private LocalDate terminateDate;
	private String terminateReason;
	private String denialReason;

}
