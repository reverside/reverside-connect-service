package za.co.reverside.service.es;

import lombok.Data;

@Data
public class Command {
	
	private String type;
	
	private Object data;
	
	private String reference;

}
