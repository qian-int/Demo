package com.example.easyexceldemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LogbackTestController
 * @Author TJ
 * @create 2023/2/3 11:46
 */
@RestController
@RequestMapping("/logback")
@Slf4j
public class LogbackTestController {

    /**
     * 测试logback邮箱发送日志
     */
    @GetMapping("/test")
    public void test(){
        //测试logback(基于标记发送日志给邮箱)邮箱发送
        Marker notify_admin = MarkerFactory.getMarker("a");
        log.error(notify_admin,"This is a serious error requiring the admin's attention", new Exception("Just testing"));
    }

}
