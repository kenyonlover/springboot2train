package com.kenyon.springboot2train.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data //生成getter,setter等函数
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor //生成无参构造函数
@Builder //建造者模式
@Component //将Author 对象交给 Spring 容器去管理
@PropertySource(value = "classpath:config/author.properties") //引入配置文件
@ConfigurationProperties(prefix = "author") //根据属性的前缀自动将 Spring 容器中对应的数据注入到对象对应的属性中
public class Author {
    private Long id;
    private Integer age;
    private String sex;
    private String name;
}
