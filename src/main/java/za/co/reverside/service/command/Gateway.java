package za.co.reverside.service.command;

import java.util.HashMap;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import za.co.reverside.service.command.Publisher;
import za.co.reverside.service.command.model.Close;
import za.co.reverside.service.domain.event.Generated;
import za.co.reverside.service.domain.model.Notification;
import za.co.reverside.service.command.adapter.MailAdapter;

import com.zenerick.core.es.Event;
import com.zenerick.core.es.EventHandler;
import com.zenerick.core.es.EventStore;
import com.zenerick.core.mapper.Mapper;

@Service
public class Gateway {
	
	@Autowired
	private EventHandler<Notification> handler;
	
	@Autowired
	private EventStore<Notification> eventStore;
	
	@Autowired
	private MailAdapter mailAdapter;
	
	@Autowired
	private Publisher publisher;

	@RabbitListener(bindings = @QueueBinding(
			value =    @Queue(value = "q.notifications.mail", durable = "true"),
			exchange = @Exchange(value = "x.notification", type = "topic"),
			key =      "za.co.reverside.service.domain.event.Generated"))
	public void handle(@Payload final Event event) throws Exception {
		Generated data = new Mapper().readValue(event.getData(), Generated.class);
		Close command = new Close();
		try {
			mailAdapter.send(data.getTo(), data.getSubject(), data.getMessage(), new HashMap<String, String>());
			command.setStatus(true);
		} catch(Exception e){
			command.setStatus(false);
		}
		publisher.publish(event.getUser(), event.getReference(), command);
	}
}
