package ru.tinyakov.picnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class OtusSpringPicnetApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtusSpringPicnetApplication.class, args);
	}

}
