package za.co.reverside.service.es;

import java.util.List;
import java.util.Map;

import za.co.reverside.service.domain.Aggregate;
import za.co.reverside.service.handler.IEvent;

import com.zenerick.core.mapper.Mapper;


public class EventManager<T extends Aggregate> {
	
	private Map<String, EventHandler<T>> map;
	
	private EventStore store;
	
	public EventManager(Map<String, EventHandler<T>> map){
		this.map = map;
	}
	
	public T build(String id, Class<T> type) throws Exception {
		List<Event> events = store.history(id, type);
		int version = 0;
		T resource = type.newInstance();
		resource.setId(id);
		resource.setVersion(version);
		for(Event event : events){
			IEvent anEvent = new Mapper().readValue(event.getData(), IEvent.class);
			map.get(event.getType()).apply(anEvent, resource);
			resource.setVersion(version++);
		}
		return resource;
	}
	
	public void publish(Object data, T resource, String user) throws Exception {
		Event event = new Event();
		event.setType(data.getClass().getCanonicalName());
		event.setData(new Mapper().writeValueAsString(data));
		event.setTime(System.currentTimeMillis());
		event.setServer("localhost");
		event.setUser(user);
		event.setAggregateType(resource.getClass().getCanonicalName());
		event.setAggregateId(resource.getId());
		event.setAggregateVersion(resource.getVersion()+1);
		store.publish(event);
	}

	

}
