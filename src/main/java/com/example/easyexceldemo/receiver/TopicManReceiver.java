package com.example.easyexceldemo.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName TopicManReceiver
 * @Author qqq
 * @create 2023/2/13 14:17
 */
@Component
@RabbitListener(queues = "topic.man")
public class TopicManReceiver {

    @RabbitHandler
    public void process(String message){
        System.out.println("DirectReceiver监听到的消息是 : " + message);
    }

}
