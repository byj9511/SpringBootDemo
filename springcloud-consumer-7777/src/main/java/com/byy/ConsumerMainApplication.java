package com.byy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableEurekaClient
//作为fegin的客户端，不能同时成为eureka的客户端
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ConsumerMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMainApplication.class,args);
    }
}
