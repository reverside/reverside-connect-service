package za.co.reverside.service.command.adapter;

import org.springframework.stereotype.Component;

@Component
public class MailAdapter {
	
	public void sendMail(String to, String subject, String message){
		System.out.println("Sending Email...");
	}

}
