package com.kristin.java_base.multithread;

/**
 * @author Kristin
 * @since 2018/9/4 10:01
 * 函数式编程
 **/
public class Demo1 {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Thread1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            throw new RuntimeException();
        }).start();
    }
}
