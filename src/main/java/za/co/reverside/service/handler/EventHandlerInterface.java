package za.co.reverside.service.handler;

import za.co.reverside.service.domain.Notification;
import za.co.reverside.service.event.DeliveredEvent;
import za.co.reverside.service.event.FailedEvent;
import za.co.reverside.service.event.GeneratedEvent;

public interface EventHandlerInterface {

	public void apply(GeneratedEvent event, Notification resource) throws Exception;
	
	public void apply(DeliveredEvent event, Notification resource) throws Exception;
	
	public void apply(FailedEvent event, Notification resource) throws Exception;
}
