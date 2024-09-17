package com.todocodeacademy.api_gateway_electro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayElectroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayElectroApplication.class, args);
	}

}
