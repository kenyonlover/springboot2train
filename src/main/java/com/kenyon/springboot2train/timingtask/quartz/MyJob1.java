package com.kenyon.springboot2train.timingtask.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 添加任务添加任务第一种方式，直接定义一个Bean：
 *
 * 关于这种定义方式说两点：
 * 首先将这个 Job 注册到 Spring 容器中。
 * 这种定义方式有一个缺陷，就是无法传参。
 */
@Component
public class MyJob1 {
    public void sayHello() {
        System.out.println("MyJob1>>>"+new Date());
    }
}