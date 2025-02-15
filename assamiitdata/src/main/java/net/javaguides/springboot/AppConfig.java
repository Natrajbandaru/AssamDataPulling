package net.javaguides.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {
	
	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
}
