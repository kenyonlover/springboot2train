package com.kenyon.springboot2train.controller;

import com.kenyon.springboot2train.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private Author author;

    @GetMapping("/defAuthor")
    public Author getAuthor(){
        return author;
    }
}
