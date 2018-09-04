package com.kristin.java_multithread.philosophers.method1;

import java.util.Random;

/**
 * @author Kristin
 * @since 2018/8/27 10:49
 **/
public class Philosopher extends Thread {
    private Chopstick first, second;
    private Random random;

    public Philosopher(Chopstick left, Chopstick right) {
        if (left.getId() < right.getId()) {
            first = left;
            second = right;
        } else {
            first = right;
            second = left;
        }
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));     // Think for a while
                synchronized (first) {                   // Grab first chopstick
                    synchronized (second) {                // Grab second chopstick
                        Thread.sleep(random.nextInt(1000)); // Eat for a while
                        System.out.println("Philosopher " + this + " eating");
                    }
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
