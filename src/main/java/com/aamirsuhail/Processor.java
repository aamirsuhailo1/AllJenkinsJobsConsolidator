package com.aamirsuhail;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Processor {
	
	public static List<String> processor(List<String> urls, List<String> projNameAndDesc) throws EmailException {
		
		int i=0;
		List<String> parthtml = new ArrayList<String>();
		 
		for(String url : urls) {
			
			Response res = given()
					.auth()
					.preemptive()
					.basic("admin","admin")
					.when()
					.get(url);
	
			ResponseBody bodyRes = res.getBody();
		
		
			String projectName = projNameAndDesc.get(i).split(",")[1];
			String description = projNameAndDesc.get(i).split(",")[0];
			String buildNumber = bodyRes.jsonPath().getString("number");
			String startedBy = bodyRes.jsonPath().getString("actions.causes.userId").replace("[", "").replace("]", "").trim();
			String gitRepoUrl = bodyRes.jsonPath().getString("actions[1].remoteUrls[0]");
			String result = bodyRes.jsonPath().getString("result");
			String duration = bodyRes.jsonPath().getString("duration");
		
		
	
		String resultBody = null;
		
		if(result.equalsIgnoreCase("SUCCESS")) {
		 resultBody = 
				"  <tr>" + 
				"  <td>"+(i+1)+"</td>" + 
				"    <td>"+projectName+"</td>" + 
				"    <td>"+description+"</td>" + 
				"    <td>"+buildNumber+"</td>" + 
				"    <td>"+startedBy+"</td>" + 
				"    <td>"+gitRepoUrl+"</td>" + 
				"    <td style=\"background-color:#33cc33;\">"+result+"</td>" + 
				"    <td>"+duration+"</td>" + 
				"  </tr>" ;
		}
		
		if(result.equalsIgnoreCase("FAILURE")) {
		 resultBody = 
				"  <tr>" + 
				"  <td>"+(i+1)+"</td>" + 
				"    <td>"+projectName+"</td>" + 
				"    <td>"+description+"</td>" + 
				"    <td>"+buildNumber+"</td>" + 
				"    <td>"+startedBy+"</td>" + 
				"    <td>"+gitRepoUrl+"</td>" + 
				"    <td style=\"background-color:#ff0000;\">"+result+"</td>" + 
				"    <td>"+duration+"</td>" + 
				"  </tr>" ;
				
		}
		
		parthtml.add(resultBody);
		
		i++;
		}
		return parthtml;
		
		
	}
		

	
	
	

}
