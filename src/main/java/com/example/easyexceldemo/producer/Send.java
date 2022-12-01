package com.example.easyexceldemo.producer;

import com.example.easyexceldemo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @ClassName Send
 * @Author TJ
 * @create 2022/11/30 16:49
 */
public class Send {
    private static final String QUEUE_NAME = "test_one";

    public static void main(String[] args) throws IOException {
        //获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();
        //声明交换机队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消息内容
        String msg = "测试rabbitmq";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println("======sent" + msg);
        channel.close();
        connection.close();
    }
}
