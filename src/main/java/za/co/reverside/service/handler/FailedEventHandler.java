package za.co.reverside.service.handler;

import za.co.reverside.service.domain.Notification;
import za.co.reverside.service.es.EventHandler;
import za.co.reverside.service.event.FailedEvent;

public class FailedEventHandler implements EventHandler<FailedEvent, Notification>{

	@Override
	public void apply(FailedEvent event, Notification resource) throws Exception {
		resource.setStatus(false);
	}

}
