package com.aamirsuhail;

import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @author aamirsuhail01@yahoo.cm
 *
 */
public class EmailHelper {
	
	
	public static void sendEmailToYahoo(String htmlBody, Properties props) throws EmailException {
		
		  String fromEmailid = props.getProperty("fromemailid").toString();
		  String password = props.getProperty("password").toString().split(";")[0];
		  String[] toemailids = props.getProperty("to_emailids").toString().split(";");
		  String subject = props.getProperty("subject").toString();
		  
		 // Create the email message
		  HtmlEmail email = new HtmlEmail();
		  email.setHostName(props.getProperty("host").toString());
		  email.setSmtpPort(Integer.parseInt(props.getProperty("port").toString()));
		  email.setAuthenticator(new DefaultAuthenticator(fromEmailid, password));
		  email.setSSLOnConnect(true);
		  email.addTo(toemailids);
		  email.setFrom(fromEmailid);
		  email.setSubject(subject);
		  
		  // set the html message
		  email.setHtmlMsg(htmlBody);

		  // send the email
		  email.send();
		  
		  System.out.println("Email sent successfully");
	}
	
	
	
}
