package com.example.easyexceldemo;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.net.SimpleSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.easyexceldemo.mapper")
public class EasyExcelDemoApplication {

    public static void main(String[] args) {
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        SimpleSocketServer simpleSocketServer = new SimpleSocketServer(lc, 9999);//该配置无法写入spring配置文件中，因为启动加载顺序问题，可以添加到环境配置中
        SpringApplication.run(EasyExcelDemoApplication.class, args);
        simpleSocketServer.start();
    }

}
