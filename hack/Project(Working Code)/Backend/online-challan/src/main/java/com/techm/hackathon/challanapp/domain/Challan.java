package com.techm.hackathon.challanapp.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Challan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "challanId", nullable = false)
	private Long challanId;
	
	@Column(name = "aadhar_no", nullable = false)
	private String aadhar_no;
	
	@Column(name = "driving_license_no", nullable = false)
	private String driving_license_no;
	
	@Column(name = "reg_no", nullable = false)
	private String reg_no;
	
	//@OneToOne(mappedBy="aadhar_no",targetEntity=ViolaterDetails.class, fetch=FetchType.EAGER)
	@OneToOne(optional=false,cascade=CascadeType.MERGE)
    //@JoinColumn(name = "aadhar_no",referencedColumnName="aadhar_no", insertable = false, updatable = false) 
	// @JoinColumn(insertable = false, updatable = false)
	private ViolaterDetails violatorDetails;
		
	@Column(name = "longitute", nullable = false)
	private String longitute;
	
	@Column(name = "lattitude", nullable = false)
	private String lattitude;	
		
	//@Column(name = "rules_violated", nullable = false)
	//private String rules_violated;
	
	@Column(name = "date", nullable = false)
	private String date;
	
	@Column(name = "policeusername", nullable = false)
	private String policeUserName;
	
	@Column(name = "challanpayment")
	private boolean challanPayment;
	
	public boolean isChallanPayment() {
		return challanPayment;
	}

	public void setChallanPayment(boolean challanPayment) {
		this.challanPayment = challanPayment;
	}

	
	//@JsonIgnore
	@OneToOne(optional=false,cascade=CascadeType.MERGE)
	private ViolationRulesDetails rules_violated;
	
	public String getPoliceUserName() {
		return policeUserName;
	}

	public ViolationRulesDetails getRules_violated() {
		return rules_violated;
	}

	public void setRules_violated(ViolationRulesDetails rules_violated) {
		this.rules_violated = rules_violated;
	}

	public void setPoliceUserName(String policeUserName) {
		this.policeUserName = policeUserName;
	}

	public Long getChallanId() {
		return challanId;
	}

	public void setChallanId(Long challanId) {
		this.challanId = challanId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Column(name = "place", nullable = false)
	private String place;
	
	

	public String getLongitute() {
		return longitute;
	}

	public void setLongitute(String longitute) {
		this.longitute = longitute;
	}

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public ViolaterDetails getViolatorDetails() {
		return violatorDetails;
	}

	public void setViolatorDetails(ViolaterDetails violatorDetails) {
		this.violatorDetails = violatorDetails;
	}

	public String getAadhar_no() {
		return aadhar_no;
	}

	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}

	
		public String getDriving_license_no() {
		return driving_license_no;
	}

	public void setDriving_license_no(String driving_license_no) {
		this.driving_license_no = driving_license_no;
	}

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}


//	public String getRules_violated() {
//		return rules_violated;
//	}
//
//	public void setRules_violated(String rules_violated) {
//		this.rules_violated = rules_violated;
//	}

}
