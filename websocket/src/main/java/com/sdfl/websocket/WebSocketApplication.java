package com.sdfl.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaClient
@Configuration
public class WebSocketApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebSocketApplication.class, args);
	}
}
