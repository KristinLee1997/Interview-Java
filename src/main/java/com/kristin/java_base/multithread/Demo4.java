package com.kristin.java_base.multithread;

import com.kristin.java_base.multithread.util.ExecuteFunction2;
import com.kristin.java_base.multithread.util.MyThread;
import com.kristin.java_base.multithread.util.Util;

/**
 * @author Kristin
 * @since 2018/9/4 10:31
 * 为函数式接口增加了返回值
 **/
public class Demo4 {
    public static void main(String[] args) {
        new MyThread(() -> {
            while (true) {
                executePerTime(1, (n) -> {
                    System.out.println("Thread1参数: " + n + '\t');
                    return 112233;
                });
            }
        }).setMyName("thread1").start();

        new MyThread(() -> {
            throw new RuntimeException();
        }).setMyName("thread2").start();
    }

    public static void executePerTime(int sec, ExecuteFunction2 function) {
        Util.quietSleep(1);
        int res = function.execute(998877);
        System.out.println("this is result " + res);
    }
}
