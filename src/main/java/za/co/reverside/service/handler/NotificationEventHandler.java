package za.co.reverside.service.handler;

import org.springframework.stereotype.Component;

import za.co.reverside.service.domain.Notification;
import za.co.reverside.service.es.EventHandler;
import za.co.reverside.service.event.CreatedEvent;

@Component
public class NotificationEventHandler  {
	
	public void apply(CreatedEvent event, T resource) throws Exception {
		System.out.println("2");
		resource.setTo(event.getTo());
		resource.setSubject(event.getSubject());
		resource.setMessage(event.getMessage());
	}

}
