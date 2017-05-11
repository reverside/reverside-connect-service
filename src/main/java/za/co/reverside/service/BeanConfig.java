package za.co.reverside.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.zenerick.core.mapper.Mapper;

@Configuration
public class BeanConfig  {
	
    @Bean
    public Mapper mapper(){
        Mapper mapper = new Mapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
    
}
