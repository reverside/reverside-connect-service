package za.co.reverside.service.es;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.zenerick.core.mapper.Mapper;

@Service
public class EventStore {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void publish(Object data, Aggregate<?> aggregate, String user) throws Exception {
		Event event = new Event();
		event.setType(data.getClass().getCanonicalName());
		event.setData(new Mapper().writeValueAsString(data));
		event.setTime(System.currentTimeMillis());
		event.setServer("localhost");
		event.setUser(user);
		event.setAggregateType(aggregate.type());
		event.setAggregateId(aggregate.id());
		event.setAggregateVersion(aggregate.version()+1);
		publish(event);
	}
	
	private void publish(Event event) {
		System.out.println("Start : Publish Event");
		mongoTemplate.insert(event);
		rabbitTemplate.convertAndSend("x.events", event.getType(), event);
		event.setPublished(true);
		mongoTemplate.save(event);
		System.out.println("Close : Publish Event");
	}
	
	public List<Event> history(String aggregateId, Class<?> aggregateType){
		String query = String.format("{ aggregateId : '%s', aggregateType : '%s' }", aggregateId, aggregateType.getCanonicalName());
		return mongoTemplate.find(new BasicQuery(query), Event.class);
	}

}
