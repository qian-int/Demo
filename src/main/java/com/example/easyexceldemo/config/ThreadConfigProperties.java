package com.example.easyexceldemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ThreadConfig
 * @Author qqq
 * @create 2023/2/14 16:58
 */
@ConfigurationProperties(prefix = "thread")
@Configuration
@Data
public class ThreadConfigProperties {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
