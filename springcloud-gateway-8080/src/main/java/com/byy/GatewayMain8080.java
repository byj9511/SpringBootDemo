package com.byy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class GatewayMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain8080.class,args);
    }
}
