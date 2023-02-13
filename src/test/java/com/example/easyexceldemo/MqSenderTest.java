package com.example.easyexceldemo;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName MqSenderTest
 * @Author qqq
 * @create 2023/2/10 17:44
 */
@SpringBootTest
public class MqSenderTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        for (int i = 0 ; i < 100 ; i++){
            amqpTemplate.convertAndSend("myQueue","第" + i + "条消息");
        }
    }
}
