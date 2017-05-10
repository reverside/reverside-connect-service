package za.co.reverside.service.command;

import lombok.Data;

@Data
public class Send {
	
	private String to;
	
	private String subject;
	
	private String message;
}
