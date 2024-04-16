package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: symphony
 * @create: 2024/04/16
 **/
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/redis")
    public String redis(){
        return "redis";
    }

    @GetMapping("/put")
    public void put(){
        redisTemplate.opsForValue().set("name", "jojo");
    }

    @GetMapping("/get")
    public String get(){

        return (String) redisTemplate.opsForValue().get("name");
    }
}
