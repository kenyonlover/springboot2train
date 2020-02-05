package com.kenyon.springboot2train;

import com.kenyon.springboot2train.config.JavaConfig;
import com.kenyon.springboot2train.itf.Food;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Springboot2trainApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2trainApplication.class, args);
    }

}
