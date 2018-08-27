package com.kristin.java_multithread.philosophers.method1;

import com.kristin.java_multithread.philosophers.dead.Chopstick;
import com.kristin.java_multithread.philosophers.dead.Philosopher;

/**
 * @author Kristin
 * @since 2018/8/27 10:37
 * 哲学家进餐问题: https://www.liuyaois.me/2018-08-20-Java%E5%B9%B6%E5%8F%91-%E5%93%B2%E5%AD%A6%E5%AE%B6%E5%B0%B1%E9%A4%90%E9%97%AE%E9%A2%98/
 **/
public class DiningPhilosophers {
    public static void main(String[] args) throws InterruptedException {
        Philosopher[] philosophers = new Philosopher[5];
        Chopstick[] chopsticks = new Chopstick[5];

        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % 5]);
            philosophers[i].start();
        }
        for (int i = 0; i < 5; i++) {
            philosophers[i].join();
        }
    }
}
