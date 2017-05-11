package za.co.reverside.service.es;

import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

@Service
public class EventStore {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	
	public void publish(Event event) {
		System.out.println("Start : Publish Event");
		mongoTemplate.insert(event);
		Message message = MessageBuilder.withBody(event.getData().getBytes()).setHeader("id", event.getAggregateId()).build();
		rabbitTemplate.send("x.events", event.getType(), message);
		event.setPublished(true);
		mongoTemplate.save(event);
		System.out.println("Close : Publish Event");
	}
	
	public List<Event> history(String aggregateId, Class<?> aggregateType){
		String query = String.format("{ aggregateId : '%s', aggregateType : '%s' }", aggregateId, aggregateType.getCanonicalName());
		return mongoTemplate.find(new BasicQuery(query), Event.class);
	}

}
