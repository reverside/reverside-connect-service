package za.co.reverside.service.es;

import java.util.List;

import com.zenerick.core.mapper.Mapper;


public class Aggregate<T> {
	
	private String  id;
	
	private T       value;
	
	private Integer version;
	
	public Aggregate(String id, Class<T> type, List<Event> events) throws Exception{
		this.id = id;
		this.version = -1;
		this.value = type.getConstructor(String.class).newInstance(id);
		for(Event event : events) {	
			apply(event); 
		}
	}
	
	public void apply(Event event) throws Exception{
		Class<?> eventType = Class.forName(event.getType());
		Object eventData = new Mapper().readValue(event.getData(), eventType);
		value.getClass().getMethod("apply", eventType).invoke(value, eventData);
		this.version++;
	}
	
	public Event handle(Object command, String user) throws Exception{
		Object anEvent= this.value.getClass().getMethod("handle", command.getClass()).invoke(value, command);
		Event event = new Event();
		event.setType(anEvent.getClass().getCanonicalName());
		event.setData(new Mapper().writeValueAsString(anEvent));
		event.setTime(System.currentTimeMillis());
		event.setServer("localhost");
		event.setUser(user);
		event.setAggregateType(value.getClass().getCanonicalName());
		event.setAggregateId(this.id);
		event.setAggregateVersion(this.getVersion()+1);
		return event;
	}
	

	public T getValue(){
		return value;
	}
	
	public int getVersion(){
		return version;
	}
		
}
