package za.co.reverside.service.es;


import java.util.List;

public class Aggregate<T>{
	
	private String id;
	
	private T value;
	
	private int version;
	
	public Aggregate(String id, Class<T> type, List<Event> events, EventHandler<T> handler) throws Exception {
		this.id = id;
		this.version = 0;
		this.value = type.getConstructor(String.class).newInstance(id);
		for(Event event : events){
			this.apply(event, handler);
		}
	}
	
	public String id(){
		return id;
	}
	
	public T value(){
		return value;
	}
	
	public int version(){
		return version;
	}
	
	public String type(){
		return value.getClass().getCanonicalName();
	}
	
	public void apply(Event event, EventHandler<T> applicator) throws Exception{
		applicator.apply(event, value);
		version++;
	}	
	
}
