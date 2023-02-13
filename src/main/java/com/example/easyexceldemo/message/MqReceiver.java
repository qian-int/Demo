package com.example.easyexceldemo.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MqReceiver
 * @Author qqq
 * @create 2023/2/10 17:41
 */
@Slf4j
@Component
public class MqReceiver {


    @RabbitListener(queues = "myQueue")
    public void process(String message){
        // @RabbitListener注解用于监听RabbitMQ，queues指定监听哪个队列
        log.info(message);
    }
}
