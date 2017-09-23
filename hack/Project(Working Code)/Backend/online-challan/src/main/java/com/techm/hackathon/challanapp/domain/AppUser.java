package com.techm.hackathon.challanapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid", nullable = false)
    private Long userid;

    @Column(name = "username", nullable = false)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "designation", nullable = false)
    private String designation;
    
    @Column(name = "base_location", nullable = false)
    private String base_location;
    
    @Column(name = "challan_count_total", nullable = false)
    private int challan_count_total;
    
    @Column(name = "challan_count_month", nullable = false)
    private String challan_count_month;
    
    @Column(name = "challan_count_year", nullable = false)
    private String challan_count_year;
    
    public AppUser() {
    }

    public AppUser(String username) {
        this.username = username;
    }

    public AppUser(long id, String username) {
        this.userid = id;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userid +
                ", username='" + username + '\'' +
                '}';
    }

	/**
	 * @return the userid
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the base_location
	 */
	public String getBase_location() {
		return base_location;
	}

	/**
	 * @param base_location the base_location to set
	 */
	public void setBase_location(String base_location) {
		this.base_location = base_location;
	}

	/**
	 * @return the challan_count_total
	 */
	public int getChallan_count_total() {
		return challan_count_total;
	}

	/**
	 * @param challan_count_total the challan_count_total to set
	 */
	public void setChallan_count_total(int challan_count_total) {
		this.challan_count_total = challan_count_total;
	}

	/**
	 * @return the challan_count_year
	 */
	public String getChallan_count_year() {
		return challan_count_year;
	}

	/**
	 * @param challan_count_year the challan_count_year to set
	 */
	public void setChallan_count_year(String challan_count_year) {
		this.challan_count_year = challan_count_year;
	}

	/**
	 * @return the challan_count_month
	 */
	public String getChallan_count_month() {
		return challan_count_month;
	}

	/**
	 * @param challan_count_month the challan_count_month to set
	 */
	public void setChallan_count_month(String challan_count_month) {
		this.challan_count_month = challan_count_month;
	}
}
