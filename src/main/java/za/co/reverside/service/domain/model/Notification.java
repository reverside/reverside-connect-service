package za.co.reverside.service.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data @NoArgsConstructor
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
