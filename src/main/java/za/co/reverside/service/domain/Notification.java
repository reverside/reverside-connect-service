package za.co.reverside.service.domain;

import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
public class Notification {
	
	@Id
	protected String id;
	
	protected String to;
	
	protected String subject;
	
	protected String message;
	
	protected Boolean status;
	
	public Notification(String id){
		this.id = id;
	}
	
}
