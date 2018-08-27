package com.kristin.java_multithread.philosophers.dead;

/**
 * @author Kristin
 * @since 2018/8/27 10:37
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
