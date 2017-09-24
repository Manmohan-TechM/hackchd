package com.techm.hackathon.challanapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techm.hackathon.challanapp.request.SMSRequest;

@RestController
@CrossOrigin
@RequestMapping(value = "/sms")
public class SMSUserController {
	
	
	@RequestMapping(value = "/user", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public Boolean sendSMS(@RequestBody SMSRequest request) {
		 //Your authentication key
        String authkey = "167999ABM0H3Ubv2iy5980e8b9";
        //Multiple mobiles numbers separated by comma
        String mobiles = request.getMobile_number();
        //Sender ID,While using route4 sender id should be 6 characters long.
        String senderId = "VERIFY";
        //Your message to send, Add URL encoding here.
        String message = "Your Challan is done at the IT Park, Chandigarh. Challan Number is " + request.getChallan_number() + ". Kindly pay in next 15 days.";
        //define route
        String route="4";

        //Prepare Url
        URLConnection myURLConnection=null;
        URL myURL=null;
        BufferedReader reader=null;

        //encoding message
        String encoded_message=URLEncoder.encode(message);

        //Send SMS API
        String mainUrl="https://control.msg91.com/api/sendhttp.php?";

        //Prepare parameter string
        StringBuilder sbPostData= new StringBuilder(mainUrl);
        sbPostData.append("authkey="+authkey);
        sbPostData.append("&mobiles="+mobiles);
        sbPostData.append("&message="+encoded_message);
        sbPostData.append("&route="+route);
        sbPostData.append("&sender="+senderId);

        //final string
        mainUrl = sbPostData.toString();
        try
        {
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            //reading response
            String response;
            while ((response = reader.readLine()) != null)
            //print response
            System.out.println(response);

            //finally close connection
            reader.close();
        }
        catch (IOException e)
        {
                e.printStackTrace();
                return false;
        }
        
        return true;
	}

}
