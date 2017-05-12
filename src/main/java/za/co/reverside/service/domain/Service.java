package za.co.reverside.service.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.reverside.service.domain.event.Delivered;
import za.co.reverside.service.domain.event.Generated;
import za.co.reverside.service.domain.model.Notification;
import za.co.reverside.service.es.EventHandler;

import com.zenerick.core.mapper.Mapper;

@Component("domain")
public class Service extends EventHandler<Notification>  {
	
	@Autowired
	private Mapper mapper;

	public void apply(Generated event, Notification notification) throws Exception {
		mapper.merge(event, notification);
	}

	public void apply(Delivered event, Notification notification) throws Exception {
		mapper.merge(event, notification);
	}

}
