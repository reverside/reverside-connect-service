package za.co.reverside.service.query;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import za.co.reverside.service.domain.model.Notification;
import za.co.reverside.service.es.Event;
import za.co.reverside.service.es.EventHandler;
import za.co.reverside.service.query.repository.NotificationRepository;

@Service
public class Synchronizer {
		
	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private EventHandler<Notification> handler;

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "q.notifications.event", durable = "true"),
			exchange = @Exchange(value = "x.notification", type = "topic"),
			key = "za.co.reverside.domain.event.*"))
	public void onEvent(@Payload Event event) throws Exception{
		System.out.println(System.currentTimeMillis());
		System.out.println("Sync Event : " + event);
		Notification resource = repository.findOne(event.getAggregateId());
		if(resource==null) resource = new Notification(event.getAggregateId());
		handler.apply(event, resource);
		repository.save(resource);
		System.out.println(System.currentTimeMillis());
	}

}
