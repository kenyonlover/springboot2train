package com.kenyon.springboot2train.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data //生成getter,setter等函数
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor //生成无参构造函数
@Builder //建造者模式
@Component //将Book 对象交给 Spring 容器去管理
@PropertySource(value = "classpath:config/book.properties") //引入配置文件
public class Book {
    @Value("${book.id}") //将属性注入到 Book 对象中
    private Long id;
    @Value("${book.name}")
    private String name;
    @Value("${book.author}")
    private String author;
}
