package com.kenyon.springboot2train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //开启定时任务
public class Springboot2trainApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot2trainApplication.class, args);
    }
}
