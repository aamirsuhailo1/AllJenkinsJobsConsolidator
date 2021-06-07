package com.aamirsuhail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.mail.EmailException;

/**
 * @author aamirsuhail01@yahoo.cm
 *
 */
public class Runner {

	public static void main(String[] args) throws EmailException, IOException {
		
		Properties props = Helper.initializeProps();
		
		String[] jobUrls = Helper.getJenkinsJobsUrls(props);
		
		List<String> subUrls = new ArrayList<String>();	
		
		String remUrl = "/api/json?pretty=true";
		
		for (int i = 0; i < jobUrls.length; i++) {			
			subUrls.add(jobUrls[i]+remUrl);
		}
		
		List<String> bldNbrs = Helper.getBuildNumbersForAllJobs(subUrls);
		List<String> projNameAndDesc = Helper.getProjNameAndDesc(subUrls);
		 
		List<String> urls = new ArrayList<String>();
		
	
		for (int i = 0; i < bldNbrs.size(); i++) {
			urls.add(jobUrls[i]+bldNbrs.get(i)+remUrl);
		}
	
		List<String> resBody = Processor.processor(urls,projNameAndDesc);
		
		String htmlTable = PostProcessor.htmlBodyBuilder(resBody);
		
		EmailHelper.sendEmailToYahoo(htmlTable,props);
		

	}

	
}
