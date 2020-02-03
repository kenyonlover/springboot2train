package com.kenyon.springboot2train;

import com.kenyon.springboot2train.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Springboot2trainApplicationTests {
    @Autowired
    private Book book;

    @Test
    void contextLoads() {
        System.out.println(book);
    }

}
