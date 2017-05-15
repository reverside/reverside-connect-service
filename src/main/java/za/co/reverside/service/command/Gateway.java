package za.co.reverside.service.command;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import za.co.reverside.service.command.adapter.MailAdapter;
import za.co.reverside.service.command.model.Close;
import za.co.reverside.service.domain.event.Generated;
import za.co.reverside.service.domain.model.Notification;
import za.co.reverside.service.es.Event;
import za.co.reverside.service.es.EventHandler;
import za.co.reverside.service.es.EventStore;

import com.zenerick.core.mapper.Mapper;

import java.util.HashMap;

@Service
public class Gateway {
	
	@Autowired
	private EventHandler<Notification> handler;
	
	@Autowired
	private EventStore store;
	
	@Autowired
	private MailAdapter adapter;
	
	@Autowired
	private RabbitTemplate template;

	@RabbitListener(bindings = @QueueBinding(
			value =    @Queue(value = "q.notifications.mail", durable = "true"),
			exchange = @Exchange(value = "x.notification", type = "topic"),
			key =      "za.co.reverside.domain.event.Generated"))
	public void handle(@Payload final Event event) throws Exception {
		Generated data = new Mapper().readValue(event.getData(), Generated.class);
		Close command = new Close();
		try {
			adapter.send(data.getTo(), data.getSubject(), data.getMessage(), new HashMap<String, String>());
			command.setStatus(true);
		} catch(Exception e){
			command.setStatus(false);
		}
		template.convertAndSend("amq.exchange",command.getClass().getCanonicalName(), command, new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().getHeaders().put("aggregateId",event.getAggregateId());
				message.getMessageProperties().getHeaders().put("user", event.getUser());
				return message;
			}
		});		
	}
}
