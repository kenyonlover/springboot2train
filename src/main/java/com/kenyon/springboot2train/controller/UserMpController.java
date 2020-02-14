package com.kenyon.springboot2train.controller;

import com.kenyon.springboot2train.entity.User;
import com.kenyon.springboot2train.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "用户管理接口_MyBatis")
@RequestMapping("/userMp")
public class UserMpController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    @ApiOperation("查询所有用户")
    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询用户")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1")
    public User getUserById(@PathVariable Long id){
        return userMapper.getUserById(id);
    }

    @PostMapping("/")
    @ApiOperation("添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳")
    })
    public Long addUser(User user){
        Long aLong = userMapper.addUser(user);
        System.out.println(user.toString());
        return aLong;
    }
}
