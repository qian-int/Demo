package com.example.easyexceldemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName ALiYunLiveConfig
 * @Author TJ
 * @create 2022/12/1 14:32
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.live")
public class ALiYunLiveConfig {

    private String pushDomin;

    private String pushIndentKey;

    private String pullDomin;

    private String pullIdentKey;

    private String appName;

    private Integer identUrlVaildTime;
}
