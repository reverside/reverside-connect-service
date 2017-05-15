package za.co.reverside.service.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zenerick.core.es.Aggregate;
import com.zenerick.core.es.Event;
import com.zenerick.core.es.EventHandler;
import com.zenerick.core.es.EventStore;

import za.co.reverside.service.command.model.Send;
import za.co.reverside.service.domain.event.Generated;
import za.co.reverside.service.domain.model.Notification;

@RestController("command")
public class Service {
	
	@Autowired
	private EventHandler<Notification> eventHandler;
	
	@Autowired
	private EventStore<Notification> eventStore;

	@RequestMapping(path = "api/notifications/send", method=RequestMethod.POST, consumes="application/json")
	public void send(@RequestParam("user") String user, @RequestParam("reference") String reference, @RequestBody Send command) throws Exception{
		System.out.println("Send Command Received : " + System.currentTimeMillis());
		List<Event> events = eventStore.query(reference); 
		Aggregate<Notification> aggregate = new Aggregate<Notification>(reference, Notification.class, events, eventHandler);
		if(aggregate.version() != 0) {
			throw new RuntimeException("Duplicate Notification Found");
		} 
		Generated event = new Generated();
		event.setTo(command.getTo());
		event.setSubject(command.getSubject());
		event.setMessage("Hello Message");
		eventStore.publish(event, aggregate, user);
		System.out.println("Send Command Processed : "+System.currentTimeMillis());
	}

}
