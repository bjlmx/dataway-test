package com.data.datawaytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.data.datawaytest.mappers")
@SpringBootApplication
public class DatawayTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatawayTestApplication.class, args);
    }

}
