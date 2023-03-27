package com.example.easyexceldemo.executor;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ScheduledThreadPoolExecutor
 * @Author qqq
 * @create 2023/2/14 9:58
 */
public class ScheduledThreadPoolExecutor {
    public static void main(String[] args) {
        java.util.concurrent.ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new java.util.concurrent.ScheduledThreadPoolExecutor(1);
        //无返回值 延迟5秒返回
        scheduledThreadPoolExecutor.schedule(()->{
            System.out.println("我要延迟5秒执行,只执行一次");
        },5000, TimeUnit.MICROSECONDS);
    }
}
