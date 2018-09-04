package com.kristin.java_base.multithread;

import com.kristin.java_base.multithread.util.MyThread;
import com.kristin.java_base.multithread.util.Util;

/**
 * @author Kristin
 * @since 2018/9/4 10:08
 * 1.将Thread.sleep() 独立成一个函数
 * 2.定义一个类继承Thread,方法中return this;可以函数式编程
 **/
public class Demo2 {
    public static void main(String[] args) {
        new MyThread(() -> {
            while (true) {
                Util.quietSleep(1);
                System.out.println("Thread1");
            }
        }).setMyName("thread1").start();

        new MyThread(() -> {
            throw new RuntimeException();
        }).setMyName("thread2").start();
    }
}


