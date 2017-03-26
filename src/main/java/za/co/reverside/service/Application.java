package za.co.reverside.service;


import com.mangofactory.swagger.plugin.EnableSwagger;

import com.zenerick.service.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MailService
@EnableSwagger
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}