package com.globant.customermanagement;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({"com.globant.customermanagement"})
@EnableDynamoDBRepositories("com.globant.productmanagement")
@EnableDiscoveryClient
@EnableSwagger2
public class CustomermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomermanagementApplication.class, args);
	}

}
