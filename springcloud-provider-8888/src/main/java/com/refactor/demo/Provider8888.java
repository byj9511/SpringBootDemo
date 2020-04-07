package com.refactor.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCaching
@MapperScan("com.refactor.demo.Dao")
@SpringBootApplication
@EnableEurekaClient

public class Provider8888 {

    public static void main(String[] args) {
        SpringApplication.run(Provider8888.class, args);
    }

}
