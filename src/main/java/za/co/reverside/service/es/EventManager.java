package za.co.reverside.service.es;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zenerick.core.mapper.Mapper;

@Component
public class EventManager<T> {
	
	@Autowired
	private EventStore store;
	
	@Autowired
	private Handler<T> handler;
	
	public Aggregate<T> aggregate(String id, Class<T> type) throws Exception {
		List<Event> events = store.history(id, type);
		System.out.println(">> "+events.size());
		Aggregate<T> aggregate = new Aggregate<T>(id, type);
		for(Event event : events){
			aggregate.apply(event, handler);
		}
		return aggregate;
	}
	
	public void publish(Object data, Aggregate<T> aggregate, String user) throws Exception {
		Event event = new Event();
		event.setType(data.getClass().getCanonicalName());
		event.setData(new Mapper().writeValueAsString(data));
		event.setTime(System.currentTimeMillis());
		event.setServer("localhost");
		event.setUser(user);
		event.setAggregateType(aggregate.type());
		event.setAggregateId(aggregate.id());
		event.setAggregateVersion(aggregate.version()+1);
		store.publish(event);
	}

}
