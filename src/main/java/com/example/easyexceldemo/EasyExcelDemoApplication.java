package com.example.easyexceldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.easyexceldemo.mapper")
public class EasyExcelDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyExcelDemoApplication.class, args);
    }

}
