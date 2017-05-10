package za.co.reverside.service.event;

import za.co.reverside.service.handler.IEvent;
import lombok.Data;

@Data
public class CreatedEvent implements IEvent{
	
	private String to;
	
	private String subject;
	
	private String message;

}
