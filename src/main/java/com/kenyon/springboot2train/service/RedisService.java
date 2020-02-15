package com.kenyon.springboot2train.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    public void helloRedis() {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("name1", "张三");
        Object k1 = ops.get("name1");
        System.out.println(k1);
    }

    public void setValue(Object key, Object value){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    public Object getValue(Object key){
        ValueOperations ops = redisTemplate.opsForValue();
        Object value = ops.get(key);
        return value;
    }
}