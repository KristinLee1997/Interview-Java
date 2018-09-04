package com.kristin.java_base.multithread;

import com.kristin.java_base.multithread.util.ExecuteFunction2;
import com.kristin.java_base.multithread.util.MyThread;
import com.kristin.java_base.multithread.util.Util;
import com.kristin.java_base.multithread.util.WhileTrueFunction;

/**
 * @author Kristin
 * @since 2018/9/4 10:37
 * 将while true独立到一个方法中
 **/
public class Demo5 {
    public static void main(String[] args) {
        new MyThread(() ->
                loop(() -> executePerTime(1, (n) -> {
                    System.out.println("Thread 1");
                    return 997788;
                }))).setMyName("t0").start();

        new MyThread(() -> {
            throw new RuntimeException();
        }).setMyName("thread2").start();
    }

    public static void executePerTime(int sec, ExecuteFunction2 function) {
        Util.quietSleep(1);
        int res = function.execute(998877);
        System.out.println("this is result " + res);
    }

    public static void loop(WhileTrueFunction function) {
        while (true) {
            function.loop();
        }
    }
}
