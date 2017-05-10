package za.co.reverside.service.command;

import org.springframework.web.bind.annotation.RestController;

import za.co.reverside.service.domain.Notification;
import za.co.reverside.service.es.EventManager;
import za.co.reverside.service.event.CreatedEvent;

@RestController
public class Service {

	private EventManager<Notification> eventManager;
	
	public void handle(String user, String id, Send command) throws Exception{
		Notification notification = eventManager.build(id, Notification.class);
		if(notification.getVersion()==0) {
			// Do Something Business Logic
			CreatedEvent event = new CreatedEvent();
			event.setTo(command.getTo());
			event.setSubject(command.getSubject());
			event.setMessage("Hello Message");
			eventManager.publish(event, notification, user);
		} else {
			throw new RuntimeException("Duplicate Notification");
		}
	}

}
