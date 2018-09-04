package com.kristin.java_base.multithread;

import com.kristin.java_base.multithread.util.ExecuteFunction;
import com.kristin.java_base.multithread.util.MyThread;
import com.kristin.java_base.multithread.util.Util;

/**
 * @author Kristin
 * @since 2018/9/4 10:28
 * 定义了函数式接口,定义了executePerTime方法
 **/
public class Demo3 {
    public static void main(String[] args) {
        new MyThread(() -> {
            while (true) {
                executePerTime(1, () -> System.out.println("Thread1"));
            }
        }).setMyName("thread1").start();

        new MyThread(() -> {
            throw new RuntimeException();
        }).setMyName("thread2").start();
    }

    public static void executePerTime(int sec, ExecuteFunction function) {
        Util.quietSleep(1);
        function.execute();
    }
}
