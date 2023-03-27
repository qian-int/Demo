package com.example.easyexceldemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyThreadConfig
 * @Author qqq
 * @create 2023/2/14 16:41
 */
@Component
@Configuration
public class MyThreadConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadConfigProperties pool){
        return new ThreadPoolExecutor(pool.getCoreSize(), pool.getMaxSize(), pool.getKeepAliveTime(), TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());//丢弃策略
    }

}
