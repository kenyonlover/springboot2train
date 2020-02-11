package com.kenyon.springboot2train.controller;

import com.kenyon.springboot2train.entity.User;
import com.kenyon.springboot2train.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户管理接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/")
    @ApiOperation("添加用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "999"),
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    })
    public String addUser(Long id, String username, @RequestParam(required = true) String address) {
        User user = new User();
        user.setId(id);
        user.setName(username);
        user.setAddress(address);
        int i = userService.addUser(user);
        return "add " + i + " user success";
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "99", required = true)
    public User getUserById(@PathVariable Long id) {
        List<User> allUsers2 = userService.getAllUsers2();
        return allUsers2.get(0);
    }

    @GetMapping("/")
    @ApiOperation("查询所有用户的接口")
    public List<User> getUsers() {
        List<User> allUsers2 = userService.getAllUsers2();
        return allUsers2;
    }

    @PutMapping("/")
    @ApiOperation("更新用户的接口")
    public String updateUserById(Long id, String username, String address) {
        User user = new User(id,username,address);
        int i = userService.updateUserById(user);
        return "update " + i + " user success";
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户的接口")
    public String deleteUserById(@PathVariable Long id) {
        int i = userService.deleteUserById(id);
        return "delete " + i + " user success";
    }
}