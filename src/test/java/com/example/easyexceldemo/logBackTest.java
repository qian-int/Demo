package com.example.easyexceldemo;

import com.example.easyexceldemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName logBackTest
 * @Author TJ
 * @create 2023/1/30 15:00
 */
@SpringBootTest
public class logBackTest {

    @Autowired
    private UserService userService;

    @Test
    void testLevel(){
        userService.testLevel();
    }

}
