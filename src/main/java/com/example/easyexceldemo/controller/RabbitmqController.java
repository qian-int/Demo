package com.example.easyexceldemo.controller;

import com.example.easyexceldemo.utils.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RabbitmqController
 * @Author qqq
 * @create 2023/2/13 11:10
 */
@RestController
@RequestMapping("/mq/test")
public class RabbitmqController {

    @Autowired
    private RabbitTemplate rabbitTemplate; //使用RabbitTemplate,这提供了接收/发送等等方法

    /**
     * 测试rabbitmq消息发送
     * @return string
     */
    @GetMapping("/sendDirectMessage")
    public Result<String> sendDirectMessage(){
        String message = "测试direct消息发送!";
        //将消息携带绑定值：TestDirectRouting 发送到交换机Te's't'DirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",message);
        return Result.success("ok");
    }

    @GetMapping("/topic")
    public Result<String> sendTopicMessage(){
        String message = "hello topic!";
        rabbitTemplate.convertAndSend("topicExchange","topic.man",message);
        return Result.success("OK!");
    }

    @GetMapping("/topicTwo")
    public Result<String> sendTopicMessageTwo(){
        String message = "hello topic!";
        rabbitTemplate.convertAndSend("topicExchange","topic.woman",message);
        return Result.success("OK!");
    }

    @GetMapping("testMessage")
    public Result<String> testMessageAck(){
        String message = "hello message!";
        rabbitTemplate.convertAndSend("lonelyDirectExchange","TestDirectRouting",message);
        return Result.success("ok");
    }


}
