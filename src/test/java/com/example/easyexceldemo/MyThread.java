package com.example.easyexceldemo;

/**
 * @ClassName MyThread
 * @Author qqq
 * @create 2023/2/10 10:21
 */
public class MyThread extends Thread{
    //实现多线程的方式之一：集成thread类

    //有50张电影票
    private int ticket = 50;

    @Override
    public synchronized void  run(){
        while (true){
            if (ticket <= 0) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "抢到了一张票,号码为：" + ticket);
            ticket = ticket - 1;
        }
    }
}
