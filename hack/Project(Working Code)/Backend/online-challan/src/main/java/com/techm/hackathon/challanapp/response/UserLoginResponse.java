package com.techm.hackathon.challanapp.response;

import com.techm.hackathon.challanapp.domain.AppUser;

public class UserLoginResponse {
	
	private AppUser user;	
	private long total_challan_count;
	private String highest_scorer;
	private String current_Location;
	/**
	 * @return the user
	 */
	public AppUser getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(AppUser user) {
		this.user = user;
	}
	/**
	 * @return the department_total_year
	 */
	public long getTotal_challan_count() {
		return total_challan_count;
	}
	/**
	 * @param department_total_year the department_total_year to set
	 */
	public void setTotal_challan_count(long total_challan_count) {
		this.total_challan_count = total_challan_count;
	}
	/**
	 * @return the highest_scorer
	 */
	public String getHighest_scorer() {
		return highest_scorer;
	}
	/**
	 * @param highest_scorer the highest_scorer to set
	 */
	public void setHighest_scorer(String highest_scorer) {
		this.highest_scorer = highest_scorer;
	}
	/**
	 * @return the current_Location
	 */
	public String getCurrent_Location() {
		return current_Location;
	}
	/**
	 * @param current_Location the current_Location to set
	 */
	public void setCurrent_Location(String current_Location) {
		this.current_Location = current_Location;
	}

}
