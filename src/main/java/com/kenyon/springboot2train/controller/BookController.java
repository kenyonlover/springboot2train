package com.kenyon.springboot2train.controller;

import com.kenyon.springboot2train.entity.Author;
import com.kenyon.springboot2train.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private Book book;

    @GetMapping("/defBook")
    public Book getAuthor(){
        return book;
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
        System.out.println(book);
        System.out.println(author);
        return "添加"+book.getName()+"("+author.getName()+" 著)成功！";
    }
}
