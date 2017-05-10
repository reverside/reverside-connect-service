package za.co.reverside.service.command;

import java.util.List;

import lombok.Data;

@Data
public class Create {
	
	private String to;
	
	private String subject;
	
	private Object data;
	
	private List<String> attachments;

}
