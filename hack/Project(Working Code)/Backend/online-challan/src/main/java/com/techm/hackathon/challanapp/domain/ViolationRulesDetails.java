package com.techm.hackathon.challanapp.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	@ManyToMany
	private List<Challan> challans;

	public List<Challan> getChallans() {
		return challans;
	}

	public void setChallans(List<Challan> challans) {
		this.challans = challans;
	}

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
