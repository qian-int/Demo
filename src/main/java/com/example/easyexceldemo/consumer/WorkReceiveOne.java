package com.example.easyexceldemo.consumer;

import com.example.easyexceldemo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * @ClassName WorkReceiveOne
 * @Author TJ
 * @create 2022/11/30 17:05
 */
public class WorkReceiveOne {
    private static final String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, InterruptedException {
        //获取链接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //监听队列 false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME,true,queueingConsumer);
        QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
        String message = new String(delivery.getBody());
        System.out.println(" [y] Received '" + message + "'");
        //如果监听队列传的false手动确定则需要添加下面代码
//        channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        //休眠一秒
        Thread.sleep(1000);
    }
}
