package com.kenyon.springboot2train.service;

import com.kenyon.springboot2train.entity.User;
import com.kenyon.springboot2train.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "c1")
public class UserMpService {
    @Autowired
    UserMapper userMapper;

    @Cacheable(key = "#id", value = "name")
    public User getUserById(Long id) {
        System.out.println("getUserById");
        return userMapper.getUserById(id);
    }

    @CachePut(key = "#user.id", value = "name")
    public User updateUserById(User user){
        userMapper.updateUserById(user);
        return user;
    }

    @CacheEvict(key = "#id", value = "name")
    public void deleteUserById(Long id){
        userMapper.deleteUserById(id);
    }
}
