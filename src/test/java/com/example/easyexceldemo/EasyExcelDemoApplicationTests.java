package com.example.easyexceldemo;

import com.alibaba.excel.EasyExcel;
import com.example.easyexceldemo.entity.People;
import com.example.easyexceldemo.listener.ReadDataListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EasyExcelDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void simpleRead(){
        String fileName = "D:\\工作簿1.xlsx";
        EasyExcel.read(fileName, People.class,new ReadDataListener()).sheet().doRead();
    }


}
