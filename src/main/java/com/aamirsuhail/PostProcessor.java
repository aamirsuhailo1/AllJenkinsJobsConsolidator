package com.aamirsuhail;

import java.util.List;

/**
 * @author aamirsuhail01@yahoo.cm
 *
 */
public class PostProcessor {
	
	public static String htmlBodyBuilder(List<String> resBody) {
		String header = "<div>" + 
				"<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">" + 
				"<tbody>" + 
				"<tr>" + 
				"<th>S.No</th>" + 
				"<th>Project Name</th>" + 
				"<th>Description</th>" + 
				"<th>Build Number</th>" + 
				"<th>Started By</th>" + 
				"<th>Git Repo</th>" + 
				"<th>Result</th>" + 
				"<th>Duration</th>" + 
				"</tr>" ;
		String result = "";
		for(String res : resBody) {
			result = result+res;
		}
		
		return header+result+"</tbody></table></div>";
				
		
		
	}


}
