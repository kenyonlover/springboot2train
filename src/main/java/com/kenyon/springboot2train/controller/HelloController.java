package com.kenyon.springboot2train.controller;

import com.kenyon.springboot2train.entity.User;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "Hello接口")
public class HelloController {

    @GetMapping("/index")
    public String index(Model model){
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User u = new User();
            u.setId((long) i);
            u.setName("名称:" + i);
            u.setAddress("地址:" + i);
            users.add(u);
        }
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/name")
    public String name(Model model){
        model.addAttribute("username", "李四");
        return "name";
    }

    @GetMapping("/global")
    public String global(Model model) {
        Map<String, Object> map = model.asMap();//全局数据
        System.out.println(map);
        int i = 1 / 0;
        return "global controller advice";
    }
}
