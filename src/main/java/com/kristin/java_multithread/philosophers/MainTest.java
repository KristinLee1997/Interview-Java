package com.kristin.java_multithread.philosophers;

/**
 * @author Kristin
 * @since 2018/8/27 10:19
 * 解决哲学家进餐问题
 **/

public class MainTest {

    public static void main(String[] args) {


        for (int i = 0; i < 5; i++) {

            Thread t = new PerThread(i);

            t.start();
        }
    }

}

