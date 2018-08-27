package com.kristin.java_multithread.philosophers.method2;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Kristin
 * @since 2018/8/27 13:15
 **/
public class Philosopher extends Thread {
    private ReentrantLock leftChopstick;
    private ReentrantLock rightChopstick;

    private Random random;

    public Philosopher(ReentrantLock leftChopstick, ReentrantLock rightChopstick) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
//                思考
                Thread.sleep(random.nextInt(1000));
                leftChopstick.lock();
                try {
                    if (rightChopstick.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        try {
                            Thread.sleep(1000);
                            System.out.println("Philosopher " + this + " eating");
                        } finally {
                            rightChopstick.unlock();
                        }
                    } else {
                        System.out.println("Philosopher " + this + " timed out");
                    }
                } finally {
                    leftChopstick.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}