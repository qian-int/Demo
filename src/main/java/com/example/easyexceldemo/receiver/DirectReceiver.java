package com.example.easyexceldemo.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName DirectReceiver
 * @Author qqq
 * @create 2023/2/13 11:30
 */
@Component
@RabbitListener(queues = "TestDirectQueue")//监听队列的名称
public class DirectReceiver {

    @RabbitHandler
    public void process(String message){
        System.out.println("DirectReceiver监听到的消息是 : " + message);
    }
}
