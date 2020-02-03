package com.kenyon.springboot2train.controller;

import com.kenyon.springboot2train.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private Book book;

    @GetMapping("/defBook")
    public Book getAuthor(){
        return book;
    }
}
