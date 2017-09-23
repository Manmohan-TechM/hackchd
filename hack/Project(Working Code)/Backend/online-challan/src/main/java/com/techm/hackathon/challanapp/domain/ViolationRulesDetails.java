package com.techm.hackathon.challanapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ViolationRulesDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ruleId", nullable = false)
	private Long ruleId;
	
	@Column(name = "ruledetail", nullable = false)
	private String ruleDetail;
	    
	@Column(name = "ruleamount", nullable = false)
	private String ruleamount;
	
//	@OneToMany(mappedBy="ruleId",targetEntity=Challan.class,
//		    fetch=FetchType.EAGER)			
//	private Challan challan_id;
//	
//
//	public Challan getChallan_id() {
//		return challan_id;
//	}
//
//	public void setChallan_id(Challan challan_id) {
//		this.challan_id = challan_id;
//	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleDetail() {
		return ruleDetail;
	}

	public void setRuleDetail(String ruleDetail) {
		this.ruleDetail = ruleDetail;
	}

	public String getRuleamount() {
		return ruleamount;
	}

	public void setRuleamount(String ruleamount) {
		this.ruleamount = ruleamount;
	}
	   
	
}
