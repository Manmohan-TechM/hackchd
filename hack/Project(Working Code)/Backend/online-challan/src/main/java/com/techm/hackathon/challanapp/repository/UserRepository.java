package com.techm.hackathon.challanapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techm.hackathon.challanapp.domain.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser,Long>{	
	@Query("Select SUM(u.challan_count_total) from AppUser u")
	Long sumChallans();	
	
	@Query("SELECT username FROM AppUser u WHERE u.challan_count_total = (SELECT MAX(v.challan_count_total) FROM AppUser v)") 
	String maxChallansUser();
}
