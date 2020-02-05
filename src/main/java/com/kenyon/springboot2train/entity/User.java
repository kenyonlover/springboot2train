package com.kenyon.springboot2train.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //生成getter,setter等函数
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor //生成无参构造函数
public class User {
    private Long id;
    private String name;
    private String address;
}