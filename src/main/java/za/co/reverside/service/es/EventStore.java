package za.co.reverside.service.es;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

@Service
public class EventStore {
	
	
	private MongoTemplate mongoTemplate;
	
	public void publish(Event event) {
		mongoTemplate.insert(event);
	}
	
	public List<Event> history(String aggregateId, Class<?> aggregateType){
		String query = String.format("{ aggregateId : '', aggregateType : '' }", aggregateId, aggregateType);
		return mongoTemplate.find(new BasicQuery(query), Event.class);
	}

}
