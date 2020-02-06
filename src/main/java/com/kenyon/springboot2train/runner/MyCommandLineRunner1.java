package com.kenyon.springboot2train.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 系统启动任务：
 *
 * 首先通过 @Compoent 注解将 MyCommandLineRunner1 注册为Spring容器中的一个 Bean。
 *
 * 添加 @Order注解，表示这个启动任务的执行优先级，因为在一个项目中，启动任务可能有多个，所以需要有一个排序。
 * @Order 注解中，数字越小，优先级越大，默认情况下，优先级的值为 Integer.MAX_VALUE，表示优先级最低。
 *
 * 在 run 方法中，写启动任务的核心逻辑，当项目启动时，run方法会被自动执行。
 * run 方法的参数，来自于项目的启动参数，即项目入口类中，main方法的参数会被传到这里。
 */
@Component
@Order(100)
public class MyCommandLineRunner1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner1>>>" + Arrays.toString(args));
    }
}