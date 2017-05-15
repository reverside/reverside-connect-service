package za.co.reverside.service.command;

import java.util.List;

import com.rabbitmq.http.client.domain.ExchangeType;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import za.co.reverside.service.command.model.Close;
import za.co.reverside.service.domain.event.Delivered;
import za.co.reverside.service.domain.model.Notification;
import za.co.reverside.service.es.Aggregate;
import za.co.reverside.service.es.Event;
import za.co.reverside.service.es.EventHandler;
import za.co.reverside.service.es.EventStore;

@Service
public class Listener {
	
	@Autowired
	private EventHandler<Notification> handler;
	
	@Autowired
	private EventStore store;

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "q.notifications.close", durable = "true"),
			exchange = @Exchange(value = "x.notification", type = "topic"),
			key = "za.co.reverside.domain.command.Close"))
	public void handle(@Header("user") String user, @Header("aggregateId") String id, @Payload Close command) throws Exception{
		System.out.println("Close Command Received : "+System.currentTimeMillis());
		List<Event> events = store.history(id, Notification.class); 
		Aggregate<Notification> aggregate = new Aggregate<>(id, Notification.class, events, handler);
		if(aggregate.version()!=1){
			throw new RuntimeException("Operation Not Allowed");
		}
		Delivered event = new Delivered();
		event.setStatus(command.getStatus());
		store.publish(event, aggregate, user);
		System.out.println("Close Command Processed : "+System.currentTimeMillis());
	}

}
