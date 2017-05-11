package za.co.reverside.service.es;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@CompoundIndex(name = "key", def = "{'aggregateId' : 1, 'aggregateVersion' : 1}", unique=true)
public class Event {
	
	@Id
	private String  id;
	
	private String  type;
	
	private String  data;
	
	private Long    time;
	
	private String  user;
	
	private String  server;  
	
	private String  aggregateType;
	
	private String  aggregateId;
	
	private Integer aggregateVersion;
	
	private Boolean published;
	
		
}
