package com.kristin.java_base.multithread.util;

/**
 * @author Kristin
 * @since 2018/9/4 10:16
 **/
public class MyThread extends Thread {
    public MyThread(Runnable target) {
        super(target);
    }

    public MyThread setMyName(String name) {
        super.setName(name);
        return this;
    }
}