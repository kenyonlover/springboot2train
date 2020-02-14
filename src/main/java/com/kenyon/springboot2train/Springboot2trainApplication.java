package com.kenyon.springboot2train;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //开启定时任务
@MapperScan(basePackages = "com.kenyon.springboot2train.mapper")//开启自动扫描mapper，如果不开启MapperScan，则需要在每个Mapper类上加@Mapper 注解
public class Springboot2trainApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot2trainApplication.class, args);
    }
}
