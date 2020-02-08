package com.kenyon.springboot2train.controller;

import com.kenyon.springboot2train.entity.Author;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "作者管理接口")
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private Author author;

    @GetMapping("/defAuthor")
    @ApiOperation("默认作者")
    public Author getAuthor(){
        return author;
    }
}
