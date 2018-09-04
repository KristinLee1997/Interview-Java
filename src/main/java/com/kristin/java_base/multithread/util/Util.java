package com.kristin.java_base.multithread.util;

/**
 * @author Kristin
 * @since 2018/9/4 10:16
 **/
public class Util {
    public static void quietSleep(int sec) {
        try {
            Thread.sleep(sec * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

