package com.kenyon.springboot2train.test;

import com.kenyon.springboot2train.config.JavaConfig;
import com.kenyon.springboot2train.itf.Food;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestCondition {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().getSystemProperties().put("people", "南方人");
        ctx.register(JavaConfig.class);
        ctx.refresh();
        Food food = (Food) ctx.getBean("food");
        System.out.println(food.showName());
    }
}
