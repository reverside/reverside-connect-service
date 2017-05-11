package za.co.reverside.service.es;

import java.lang.reflect.Method;


import com.zenerick.core.mapper.Mapper;

public abstract class Handler<T> {
	
	public void apply(Event event, T resource) throws Exception{
		Class<?> eventType = Class.forName(event.getType());
		Object eventObject = new Mapper().readValue(event.getData(), eventType);
		Method method = this.getClass().getMethod("apply", eventType, resource.getClass());
		method.invoke(this, eventObject, resource);
	}
}
