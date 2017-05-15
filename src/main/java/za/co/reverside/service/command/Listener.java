package za.co.reverside.service.command;

import java.util.List;

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

import com.zenerick.core.es.Aggregate;
import com.zenerick.core.es.Event;
import com.zenerick.core.es.EventHandler;
import com.zenerick.core.es.EventStore;

@Service
public class Listener {
	
	@Autowired
	private EventHandler<Notification> eventHandler;
	
	@Autowired
	private EventStore<Notification> eventStore;
	
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "q.notifications.close", durable = "true"), 
			exchange = @Exchange(value = "x.notification", type = "topic"),	
			key = "za.co.reverside.service.domain.command.Close"))
	public void handle(@Header("user") String user, @Header("reference") String reference, @Payload Close command) throws Exception{
		System.out.println("Close Command Received : "+System.currentTimeMillis());
		List<Event> events = eventStore.query(reference); 
		Aggregate<Notification> aggregate = new Aggregate<>(reference, Notification.class, events, eventHandler);
		if(aggregate.version()!=1){
			throw new RuntimeException("Operation Not Allowed");
		}
		Delivered event = new Delivered();
		event.setStatus(command.getStatus());
		eventStore.publish(event, aggregate, user);
		System.out.println("Close Command Processed : "+System.currentTimeMillis());
	}
}
