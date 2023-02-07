package com.example.easyexceldemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Author TJ
 * @create 2023/1/30 14:58
 */
@Service
public class UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public void testLevel(){
        for (int i = 0;i<10000;i++){
            logger.trace(" --- trace --- ");
            logger.debug(" --- debug --- ");
            logger.info(" --- info --- ");
            logger.warn(" --- warn --- ");
            logger.error(" --- error --- ");
        }
    }
}
