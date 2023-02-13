package com.example.easyexceldemo;

/**
 * @ClassName MyThreadTest
 * @Author qqq
 * @create 2023/2/10 10:25
 */
public class MyThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        new Thread(myThread,"A").start();
        new Thread(myThread,"B").start();
        new Thread(myThread,"c").start();
    }


}
