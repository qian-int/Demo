package com.example.easyexceldemo.controller;

import com.example.easyexceldemo.service.CompletableFutureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CompletableFutureController
 * @Author qqq
 * @create 2023/2/14 10:24
 */
@RestController
@RequestMapping("/completable")
@Slf4j
public class CompletableFutureController {

    @Autowired
    private CompletableFutureService completableFutureService;

    @RequestMapping("/serial")
    public String test(){
        String times = completableFutureService.selectBySerial();
        return "任务串行共计耗时" + times;
    }

    @RequestMapping("/parallel")
    public String testOne(){
        String times = completableFutureService.selectByParallel();
        return "多任务编排共计耗时" + times;
    }


}
