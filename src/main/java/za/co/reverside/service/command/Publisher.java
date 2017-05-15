package za.co.reverside.service.command;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.reverside.service.command.model.Close;

@Component
public class Publisher {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void publish(final String user, final String reference, final Close command){
		rabbitTemplate.convertAndSend("x.notification",command.getClass().getCanonicalName(), command, new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().getHeaders().put("user", user);
				message.getMessageProperties().getHeaders().put("reference", reference);
				return message;
			}
		});		
	}


}
