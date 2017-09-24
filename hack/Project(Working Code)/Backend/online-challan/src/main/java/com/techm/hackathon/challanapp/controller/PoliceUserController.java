package com.techm.hackathon.challanapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;
import javax.naming.AuthenticationException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techm.hackathon.challanapp.domain.AppUser;
import com.techm.hackathon.challanapp.domain.Challan;
import com.techm.hackathon.challanapp.domain.ViolationRulesDetails;
import com.techm.hackathon.challanapp.repository.ChallanRepository;
import com.techm.hackathon.challanapp.repository.UserRepository;
import com.techm.hackathon.challanapp.repository.ViolationRulesRepository;
import com.techm.hackathon.challanapp.request.ChallanHistoryRequest;
import com.techm.hackathon.challanapp.request.ChallanRequest;
import com.techm.hackathon.challanapp.request.UserLoginRequest;
import com.techm.hackathon.challanapp.response.ChallanResponse;
import com.techm.hackathon.challanapp.response.UserLoginResponse;

@RestController
@CrossOrigin
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class PoliceUserController {

	@Inject
	UserRepository userRepository;
	@Inject
	ChallanRepository challanRepository;
	@Inject
	ViolationRulesRepository violationRulesRepository;

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AppUser create(@RequestBody AppUser user) {
		return userRepository.save(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AppUser> findAll() {
		final List<AppUser> resultList = new ArrayList<>();
		final Iterable<AppUser> all = userRepository.findAll();
		all.forEach(new Consumer<AppUser>() {
			@Override
			public void accept(AppUser appUser) {
				resultList.add(appUser);
			}
		});
		return resultList;
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AppUser changePassword(@RequestBody UserLoginRequest userRequest) throws AuthenticationException {
		
		UserLoginResponse res=new UserLoginResponse();
		AppUser userUpdated= findAll()
				.parallelStream()
				.filter(user -> user.getUsername().equalsIgnoreCase(
						userRequest.getUsername()))
				.findFirst().orElse(null);
		
		userUpdated.setPassword(userRequest.getPassword());
		return userUpdated;
	}

	@RequestMapping(value = "/authentication", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserLoginResponse authentication(@RequestBody UserLoginRequest userRequest) throws AuthenticationException {
		UserLoginResponse res=new UserLoginResponse();
		AppUser returnUser= findAll()
				.parallelStream()
				.filter(user -> user.getUsername().equalsIgnoreCase(
						userRequest.getUsername()))
				.findFirst().orElse(null);
		
		if(null!=returnUser && returnUser.getPassword().equals(userRequest.getPassword()))
		{
			res.setUser(returnUser);
			res.setTotal_challan_count(userRepository.sumChallans());
			res.setHighest_scorer(userRepository.maxChallansUser());
			return res;
		}
		else throw new AuthenticationException("Wrong username or password");
		
	}
	
	@RequestMapping(value = "/challan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ChallanResponse doChallan(@RequestBody final Challan challan) {
		ChallanResponse res = new ChallanResponse();
		Challan challanData = challanRepository.save(challan);
		final List<Challan> listofChallan = new ArrayList<>();
		final Iterable<Challan> all = challanRepository.findAll();
		all.forEach(new Consumer<Challan>() {
			@Override
			public void accept(Challan ch) {
				listofChallan.add(ch);
			}
		});
		List<Challan> prevlistofChallansforCulprit = new ArrayList<>();
		listofChallan.forEach(ch -> {
			if(ch.getAadhar_no().equalsIgnoreCase(challan.getAadhar_no()))
			{
				prevlistofChallansforCulprit.add(ch);
			}
		});
		res.setChallanList(prevlistofChallansforCulprit);
		// Updating policeman challan count
		AppUser policeUser= findAll()
				.parallelStream()
				.filter(user -> user.getUsername().equalsIgnoreCase(
						challan.getPoliceUserName()))
				.findFirst().orElse(null);
		policeUser.setChallan_count_total(policeUser.getChallan_count_total() + 1);		
		userRepository.save(policeUser);
		res.setChallan_number(String.valueOf(challanData.getChallanId()));
		return res;		
	}
	
	@RequestMapping(value = "/getChallanDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Challan> getChallanDetails(@RequestBody ChallanHistoryRequest challanHistoryRequest) {
		List<Challan> challanList = new ArrayList<>();
		if(null != challanHistoryRequest.getAadhar_no() && !challanHistoryRequest.getAadhar_no().isEmpty())
			challanList = challanRepository.getChallanByAadharNo(challanHistoryRequest.getAadhar_no());
		else if(null != challanHistoryRequest.getChallanNo() && !challanHistoryRequest.getChallanNo().isEmpty())
			challanList = challanRepository.getChallanByAadharNo(challanHistoryRequest.getAadhar_no());		
		
		return challanList;
	}
	
	@RequestMapping(value = "/payChallan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Challan> payChallan(@RequestBody ChallanHistoryRequest challanHistoryRequest) {
		List<Challan> challanList = new ArrayList<>();
		if(null != challanHistoryRequest.getAadhar_no() && !challanHistoryRequest.getAadhar_no().isEmpty())
			challanList = challanRepository.payChallanByAadharNo(challanHistoryRequest.getAadhar_no());
		else if(null != challanHistoryRequest.getChallanNo() && !challanHistoryRequest.getChallanNo().isEmpty())
			challanList = challanRepository.payChallanByAadharNo(challanHistoryRequest.getAadhar_no());		
		
		return challanList;
	}
	@RequestMapping(value = "/adminauth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserLoginResponse adminAuth(@RequestBody UserLoginRequest userRequest) throws AuthenticationException {
		UserLoginResponse res=new UserLoginResponse();
		AppUser adminUser= findAll()
				.parallelStream()
				.filter(user -> user.getUsername().equalsIgnoreCase(
						userRequest.getUsername()))
				.findFirst().orElse(null);

		if(null!=adminUser && !adminUser.getPassword().isEmpty() &&adminUser.getPassword().equals(userRequest.getPassword()))
		{
			if(adminUser.isAdmin()) 
			{
				res.setUser(adminUser);
				// Find total number of challans made
				System.out.println(userRepository.sumChallans());
				res.setTotal_challan_count(userRepository.sumChallans());
				// Max challan made by user	
				System.out.println(userRepository.maxChallansUser());
				res.setHighest_scorer(userRepository.maxChallansUser());						
				return res;
			}
			else throw new AuthenticationException("The User is not admin.");
		}
		else throw new AuthenticationException("Wrong username or password.");
		
	}

	@RequestMapping(value = "/getViolationRules", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ViolationRulesDetails> violationRules() {
		final List<ViolationRulesDetails> resultList = new ArrayList<>();
		final Iterable<ViolationRulesDetails> all = violationRulesRepository.findAll();
		all.forEach(new Consumer<ViolationRulesDetails>() {
			@Override
			public void accept(ViolationRulesDetails violationRulesDetails) {
				resultList.add(violationRulesDetails);
			}
		});
		return resultList;
	}
	
	@RequestMapping(value = "/addViolationRule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ViolationRulesDetails create(@RequestBody ViolationRulesDetails violationRule) {
		return violationRulesRepository.save(violationRule);
	}
	
	

}
