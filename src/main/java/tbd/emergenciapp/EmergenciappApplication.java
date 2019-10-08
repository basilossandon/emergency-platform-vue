package tbd.emergenciapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
@SpringBootApplication
public class EmergenciappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmergenciappApplication.class, args);
	}
	
}
