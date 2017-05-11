package za.co.reverside.service.es;


import lombok.Data;

@Data
public class Aggregate<T>{
	
	private String id;
	
	private T value;
	
	private int version;
	
	public Aggregate(String id, Class<T> type) throws Exception {
		this.id = id;
		this.version = 0;
		this.value = type.getConstructor(String.class).newInstance(id);
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
	
	public void apply(Event event, Handler<T> handler) throws Exception{
		handler.apply(event, value);
		version++;
	}	
	
}
