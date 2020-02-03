package com.kenyon.springboot2train.test;

import com.kenyon.springboot2train.config.JavaConfig;
import com.kenyon.springboot2train.config.JavaConfig2;
import com.kenyon.springboot2train.itf.Food;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestCondition2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("南方人");
        ctx.register(JavaConfig2.class);
        ctx.refresh();
        Food food = (Food) ctx.getBean("food");
        System.out.println(food.showName());
    }
}
