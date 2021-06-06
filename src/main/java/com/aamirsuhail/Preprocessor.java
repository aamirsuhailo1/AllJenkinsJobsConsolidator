package com.aamirsuhail;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.mail.EmailException;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Preprocessor {

	public static void main(String[] args) throws EmailException, IOException {
		
		Properties props = initializeProps();
		
		String[] jobUrls = getJenkinsJobsUrls(props);
		
		List<String> subUrls = new ArrayList();	
		
		String remUrl = "/api/json?pretty=true";
		
		for (int i = 0; i < jobUrls.length; i++) {			
			subUrls.add(jobUrls[i]+remUrl);
		}
		
		List<String> bldNbrs = getBuildNumbersForAllJobs(subUrls);
		List<String> projNameAndDesc = getProjNameAndDesc(subUrls);
		 
		List<String> urls = new ArrayList();
		
	
		for (int i = 0; i < bldNbrs.size(); i++) {
			urls.add(jobUrls[i]+bldNbrs.get(i)+remUrl);
		}
	
		List<String> resBody = Processor.processor(urls,projNameAndDesc);
		
		String htmlTable = PostProcessor.htmlBodyBuilder(resBody);
		
		EmailHelper.sendEmailToYahoo(htmlTable,props);
		
		//EmailHelper.sendEmailToGmail(htmlTable,props);

	}

	public static String[] getJenkinsJobsUrls(Properties props) throws IOException {
		
		String urlsProps[] = props.get("jenkinsjobsurls").toString().split(";");
		return urlsProps;
	}

	private static Properties initializeProps() throws IOException {
		File file = new File("./src/main/java/com/aamirsuhail/config/config.properties");
		FileInputStream fin = new FileInputStream(file);
		Properties props = new Properties();
		props.load(fin);
		return props;
	}

	public static List<String> getBuildNumbersForAllJobs(List<String> subUrls) {
		
		List<String> bldNbrs = new ArrayList();
		
		for(String surl : subUrls) {
			Response response = given()
					.auth()
					.preemptive()
					.basic("admin","admin")
					.when()
					.get(surl);
	
			ResponseBody body = response.getBody();
			
			String buildNumber = body.jsonPath().getString("lastBuild.number");
			
			bldNbrs.add("/"+buildNumber);
	}
		return bldNbrs;
	}
	
	public static List<String> getProjNameAndDesc(List<String> subUrls) {
		
		List<String> projDesc = new ArrayList<String>();
		
		for(String surl : subUrls) {
			Response response = given()
					.auth()
					.preemptive()
					.basic("admin","admin")
					.when()
					.get(surl);
	
			ResponseBody body = response.getBody();
			
			String description = body.jsonPath().getString("description");				
			String projectName = body.jsonPath().getString("displayName");
			projDesc.add(description+","+projectName);
			
		}
		return projDesc;
	
	}
}
