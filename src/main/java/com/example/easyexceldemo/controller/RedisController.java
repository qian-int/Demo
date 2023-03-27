package com.example.easyexceldemo.controller;

import com.example.easyexceldemo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisController
 * @Author qqq
 * @create 2023/3/27 10:48
 */
@RequestMapping("/redis")
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/test")
    public Result<String> testRedis(){
        //添加一个string类型的数据键test,值hello
        redisTemplate.opsForValue().set("qqq","qqq",60, TimeUnit.SECONDS);
        return Result.success("成功!");
    }

    @GetMapping("/getRedisValue")
    public Result<String> getRedisValue() {
        String test = redisTemplate.opsForValue().get("qqq");
        return Result.success(test);
    }

    @GetMapping("/delete")
    public void delete(){
        redisTemplate.delete("test");
    }



}
