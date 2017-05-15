package za.co.reverside.service.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.reverside.service.command.model.Send;
import za.co.reverside.service.domain.event.Generated;
import za.co.reverside.service.domain.model.Notification;
import za.co.reverside.service.es.Aggregate;
import za.co.reverside.service.es.Event;
import za.co.reverside.service.es.EventStore;
import za.co.reverside.service.es.EventHandler;

@RestController("command")
public class Service {
	
	@Autowired
	private EventHandler<Notification> handler;
	
	@Autowired
	private EventStore store;

	@RequestMapping(path = "api/notifications/send", method=RequestMethod.POST, consumes="application/json")
	public void handle(@RequestParam("user") String user, @RequestParam("id") String id, @RequestBody Send command) throws Exception{
		System.out.println("Send Command Received : " + System.currentTimeMillis());
		List<Event> events = store.history(id, Notification.class); 
		Aggregate<Notification> aggregate = new Aggregate<>(id, Notification.class, events, handler);
		if(aggregate.version() != 0) {
			throw new RuntimeException("Duplicate Notification");
		} 
		Generated event = new Generated();
		event.setTo(command.getTo());
		event.setSubject(command.getSubject());
		event.setMessage("Hello Message");
		store.publish(event, aggregate, user);
		System.out.println("Send Command Processed : "+System.currentTimeMillis());
	}

}
