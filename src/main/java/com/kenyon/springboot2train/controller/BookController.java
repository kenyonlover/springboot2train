package com.kenyon.springboot2train.controller;

import com.kenyon.springboot2train.entity.Author;
import com.kenyon.springboot2train.entity.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "书籍管理接口") // @Api 注解可以用来标记当前 Controller 的功能。
@RequestMapping("/book")
public class BookController {
    @Autowired
    private Book book;

    @GetMapping("/defBook")
    @ApiOperation("默认书籍")
    public Book getAuthor() {
        return book;
    }

    @PostMapping("/addBook")
    @ApiOperation("添加书籍") // @ApiOperation 注解用来标记一个方法的作用。
    @ApiImplicitParams({ //如果有多个参数，则需要使用多个 @ApiImplicitParam 注解来描述，多个 @ApiImplicitParam 注解需要放在一个 @ApiImplicitParams 注解中。
            @ApiImplicitParam(name = "book", value = "书籍"), //@ApiImplicitParam 注解用来描述一个参数，可以配置参数的中文含义，也可以使用defaultValue给参数设置默认值，这样在接口测试的时候可以避免手动输入。
            @ApiImplicitParam(name = "author", value = "作者", required = true)
    })
    public String addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
        System.out.println(book);
        System.out.println(author);
        return "添加" + book.getName() + "(" + author.getName() + " 著)成功！";
    }
}
