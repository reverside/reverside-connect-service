package za.co.reverside.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import za.co.reverside.service.domain.model.Notification;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.zenerick.core.es.EventStore;
import com.zenerick.core.mapper.Mapper;

@Configuration
public class BeanConfig {
	
    @Bean
    public Mapper mapper(){
        Mapper mapper = new Mapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
    
    @Bean
    public EventStore<Notification> eventStore(MongoTemplate mongoTemplate, RabbitTemplate rabbitTemplate){
    	return new EventStore<Notification>(mongoTemplate, rabbitTemplate);
    }

}
