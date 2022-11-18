package com.example.easyexceldemo.utils;

import java.util.UUID;

/**
 * @ClassName UuidUtils
 * @Author qqq
 * @create 2022/11/17 11:54
 */
public class UuidUtils {

    /**
     * 获取随机的uuid
     * @return string
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString();
    }

    public static String sampleUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
