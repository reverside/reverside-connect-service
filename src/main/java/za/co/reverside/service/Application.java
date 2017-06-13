package za.co.reverside.service;

import org.springframework.boot.SpringApplication;

import com.zenerick.service.notification.Service;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Service
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Notification API")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.zenerick.service.notification"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfoBuilder()
				.title("Reverside Notification API")
				.description("API for notifications through different channels.")
				.contact(new Contact(
					"Reverside Innovation Lab",
					"http://reverside.co.za/innovations-lab",
					"manmay.moahnty@reverside.co.za"))
				.license("License")
				.licenseUrl("http://reverside.co.za/license")
				.version("0.0.1")
				.build());
	}


}