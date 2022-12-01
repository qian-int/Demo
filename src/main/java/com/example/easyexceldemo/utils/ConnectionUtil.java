package com.example.easyexceldemo.utils;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @ClassName ConnectionUtil
 * @Author TJ
 * @create 2022/11/30 16:39
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException {
        //定义线程工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务地址
        connectionFactory.setHost("127.0.0.1");
        //端口
        connectionFactory.setPort(5672);
        //设置账户信息,用户名,密码，vhost
        connectionFactory.setVirtualHost("testHost");
        connectionFactory.setUsername("qqq");
        connectionFactory.setPassword("qianqiang0216");
        //通过工程获取连接
        return connectionFactory.newConnection();
    }

}
