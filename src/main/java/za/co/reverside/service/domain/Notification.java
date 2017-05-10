package za.co.reverside.service.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Notification extends Aggregate {
	
	protected String to;
	
	protected String subject;
	
	protected String message;
	
	protected Boolean status;
	
	public Notification(String id){
		this.id = id;
		this.version = 0;
	}

}
