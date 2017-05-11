package za.co.reverside.service.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.reverside.service.command.Send;
import za.co.reverside.service.domain.Notification;
import za.co.reverside.service.es.Aggregate;
import za.co.reverside.service.es.Event;
import za.co.reverside.service.es.EventManager;
import za.co.reverside.service.event.GeneratedEvent;

import java.util.List;

@RestController
public class CommandHandler {
	
	@Autowired
	private EventManager<Notification> eventManager;

	@RequestMapping(path = "/api/command/send", method=RequestMethod.POST, consumes="application/json")
	public void handle(@RequestParam("user") String user, @RequestParam("id") String id, @RequestBody Send command) throws Exception{
		System.out.println(System.currentTimeMillis());
		Aggregate<Notification> aggregate = eventManager.aggregate(id, Notification.class);
		System.out.println(aggregate.getVersion());
		if(aggregate.getVersion()==0) {
			// Do Something Business Logic
			GeneratedEvent event = new GeneratedEvent();
			event.setTo(command.getTo());
			event.setSubject(command.getSubject());
			event.setMessage("Hello Message");
			eventManager.publish(event, aggregate, user);
			System.out.println(System.currentTimeMillis());
		} else {
			System.out.println(System.currentTimeMillis());
			throw new RuntimeException("Duplicate Notification");
		}

	}

}
