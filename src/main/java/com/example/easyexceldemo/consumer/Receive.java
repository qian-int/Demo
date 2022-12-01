package com.example.easyexceldemo.consumer;

import com.example.easyexceldemo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * @ClassName Receive
 * @Author TJ
 * @create 2022/11/30 16:56
 */
public class Receive {
    private static final String QUEUE_NAME = "test_one";

    public static void main(String[] args) throws IOException, InterruptedException {
        //获取链接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义队列消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,queueingConsumer);
        //获取消息
        QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
        String result = new String(delivery.getBody());
        System.out.println(result);
    }
}
