package za.co.reverside.service.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zenerick.core.mapper.Mapper;

import za.co.reverside.service.domain.Notification;
import za.co.reverside.service.es.Handler;
import za.co.reverside.service.event.DeliveredEvent;
import za.co.reverside.service.event.FailedEvent;
import za.co.reverside.service.event.GeneratedEvent;

@Component
public class EventHandler extends Handler<Notification>  {
	
	@Autowired
	private Mapper mapper;

	public void apply(GeneratedEvent event, Notification notification) throws Exception {
		System.out.println("[Start] : GeneratedEvent");
		mapper.merge(event, notification);
		System.out.println("[Close] : GeneratedEvent");
	}

	public void apply(DeliveredEvent event, Notification notification) throws Exception {
		mapper.merge(event, notification);
	}

	public void apply(FailedEvent event, Notification notification) throws Exception  {
		mapper.merge(event, notification);
	}

}
