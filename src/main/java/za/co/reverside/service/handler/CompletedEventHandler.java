package za.co.reverside.service.handler;


import za.co.reverside.service.domain.Notification;
import za.co.reverside.service.es.EventHandler;
import za.co.reverside.service.event.CompletedEvent;

import com.zenerick.core.mapper.Mapper;

public class CompletedEventHandler implements EventHandler<Notification>{
	
	Mapper mapper = new Mapper();

	@Override
	public void apply(CompletedEvent event, Notification resource) throws Exception {
		resource.setStatus(true);
	}

}
