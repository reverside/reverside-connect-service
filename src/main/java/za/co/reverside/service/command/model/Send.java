package za.co.reverside.service.command.model;

import lombok.Data;

@Data
public class Send {
	
	private String to;
	
	private String subject;
	
	private String message;
}
