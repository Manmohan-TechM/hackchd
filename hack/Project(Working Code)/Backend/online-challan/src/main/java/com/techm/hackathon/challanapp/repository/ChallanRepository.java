package com.techm.hackathon.challanapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techm.hackathon.challanapp.domain.Challan;
@Repository
public interface ChallanRepository  extends CrudRepository<Challan,Long>{
	@Query("FROM Challan u WHERE u.aadhar_no = ?1") 
	List<Challan> getChallanByAadharNo(String aadhar_no);
	
	@Query("FROM Challan u WHERE u.challanId = ?1") 
	List<Challan> getChallanByChallanId(Long challanId);
	
	@Query("UPDATE Challan c SET c.challanPayment = true WHERE c.aadhar_no = ?1 AND c.challanPayment = false") 
	List<Challan> payChallanByAadharNo(String aadhar_no);
	
	@Query("UPDATE Challan c SET c.challanPayment = true WHERE c.challanId = ?1 AND c.challanPayment = false") 
	List<Challan> payChallanByChallanId(Long challanId);
}
