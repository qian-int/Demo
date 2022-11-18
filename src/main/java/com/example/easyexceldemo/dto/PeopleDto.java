package com.example.easyexceldemo.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName PeopleDto
 * @Author qqq
 * @create 2022/11/17 11:26
 */
@Data
public class PeopleDto {

    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别（0：女 1：男）
     */
    private String sex;
    /**
     * 生日
     */
    private String birthday;
}
