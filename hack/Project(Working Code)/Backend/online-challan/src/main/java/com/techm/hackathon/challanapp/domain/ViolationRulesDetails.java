package com.techm.hackathon.challanapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ViolationRulesDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ruleId", nullable = false)
	private Long ruleId;
	
	@Column(name = "ruledetail", nullable = false)
	private String ruleDetail;
	    
	@Column(name = "ruleamount", nullable = false)
	private Integer ruleAmount;
	
	
	
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

	public Integer getRuleamount() {
		return ruleAmount;
	}

	public void setRuleamount(Integer ruleamount) {
		this.ruleAmount = ruleamount;
	}
	   
	
}
