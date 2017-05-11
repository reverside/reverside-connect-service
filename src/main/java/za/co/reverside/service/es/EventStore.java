package za.co.reverside.service.es;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

@Service
public class EventStore {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void publish(Event event) {
		System.out.println("Start : Publish Event");
		mongoTemplate.insert(event);
		System.out.println("Close : Publish Event");
	}
	
	public List<Event> history(String aggregateId, Class<?> aggregateType){
		String query = String.format("{ aggregateId : '%s', aggregateType : '%s' }", aggregateId, aggregateType.getCanonicalName());
		return mongoTemplate.find(new BasicQuery(query), Event.class);
	}

}
