package com.example.easyexceldemo.service.impl;

import com.example.easyexceldemo.service.CompletableFutureService;
import com.example.easyexceldemo.vo.PeopleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName CompletableFutureServiceImpl
 * @Author qqq
 * @create 2023/2/14 10:26
 */
@Service
@Slf4j
public class CompletableFutureServiceImpl implements CompletableFutureService {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public String selectBySerial() {
        LocalDateTime startTime = LocalDateTime.now();
        PeopleVo peopleVo = new PeopleVo();
        //1、开始异步执行业务1
        CompletableFuture<Integer> businessOneCompletableFuture = CompletableFuture.supplyAsync(()->{
            log.info("开始执行线程1相关逻辑");
            Integer businessOneResult = getBusinessOneResult();
            peopleVo.setBusiness(businessOneResult);
            return businessOneResult;
        },threadPoolExecutor);
        //2、异步执行业务2,但基于结果1
        CompletableFuture<Void> businessTwoCompletableFuture = businessOneCompletableFuture.thenAcceptAsync(businessOneResult->{
            log.info("开始执行线程2相关逻辑");
            Integer businessTwoResult = getBusinessTwoResult(businessOneResult);
            peopleVo.setBusinessTwo(businessTwoResult);
        },threadPoolExecutor);
        //3、异步执行业务3,基于结果1
        CompletableFuture<Void> businessThreeCompletableFuture = businessOneCompletableFuture.thenAcceptAsync(businessOneResult->{
            log.info("开始执行线程3相关逻辑");
            Integer businessThreeResult = getBusinessThreeResult(businessOneResult);
            peopleVo.setBusinessThree(businessThreeResult);
        },threadPoolExecutor);
        //4、异步执行业务4,基于结果1
        CompletableFuture<Void> businessFourCompletableFuture = businessOneCompletableFuture.thenAcceptAsync(businessOneResult->{
            log.info("开始执行线程4相关逻辑");
            Integer businessFourResult = getBusinessFourResult(businessOneResult);
            peopleVo.setBusinessFour(businessFourResult);
        },threadPoolExecutor);
        //5、异步执行业务5,基于结果1
        CompletableFuture<Void> businessFiveCompletableFuture = businessOneCompletableFuture.thenAcceptAsync(businessOneResult->{
            log.info("开始执行线程5相关逻辑");
            Integer businessFiveResult = getBusinessFiveResult(businessOneResult);
            peopleVo.setBusinessFive(businessFiveResult);
        },threadPoolExecutor);
        //6、多任务组合执行
        CompletableFuture[] args = {businessOneCompletableFuture,businessTwoCompletableFuture,businessThreeCompletableFuture,businessFourCompletableFuture,businessFiveCompletableFuture};
        CompletableFuture.allOf(args).join();
        log.info("用户信息:{}",peopleVo);
        LocalDateTime endTime = LocalDateTime.now();
        log.info("====================多任务组合执行共耗时:{}s=====================", ChronoUnit.SECONDS.between(startTime,endTime));
        return ChronoUnit.SECONDS.between(startTime,endTime) + "";
    }

    @Override
    public String selectByParallel() {
        LocalDateTime startTime = LocalDateTime.now();
        PeopleVo peopleVo = new PeopleVo();
        log.info("开始串行执行相关逻辑");
        //1、业务1结果处理
        Integer businessOneResult = getBusinessOneResult();
        peopleVo.setBusiness(businessOneResult);
        //2、业务2结果处理，基于结果1
        Integer businessTwoResult = getBusinessTwoResult(businessOneResult);
        peopleVo.setBusinessTwo(businessTwoResult);
        //3、业务3结果处理，基于结果1
        Integer businessThreeResult = getBusinessThreeResult(businessOneResult);
        peopleVo.setBusinessThree(businessThreeResult);
        //4、业务4结果处理，基于结果1
        Integer businessFourResult = getBusinessFourResult(businessOneResult);
        peopleVo.setBusinessFour(businessFourResult);
        //5、业务5结果处理,基于结果1
        Integer businessFiveResult = getBusinessFiveResult(businessOneResult);
        peopleVo.setBusinessFive(businessFiveResult);
        LocalDateTime endTime = LocalDateTime.now();
        log.info("用户信息:{}",peopleVo);
        log.info("==============================Serial total execute time:{}s=========================",ChronoUnit.SECONDS.between(startTime,endTime));
        return ChronoUnit.SECONDS.between(startTime,endTime) + "";
    }

    private Integer getBusinessOneResult(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private Integer getBusinessTwoResult(Integer businessOneResult){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return businessOneResult + 2;
    }

    private Integer getBusinessThreeResult(Integer businessOneResult){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return businessOneResult + 3;
    }

    private Integer getBusinessFourResult(Integer businessOneResult){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return businessOneResult + 4;
    }

    private Integer getBusinessFiveResult(Integer businessOneResult){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return businessOneResult + 5;
    }

}
