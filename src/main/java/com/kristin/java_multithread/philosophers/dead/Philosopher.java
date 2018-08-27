package com.kristin.java_multithread.philosophers.dead;

import java.util.Random;

/**
 * @author Kristin
 * @since 2018/8/27 10:36
 **/
public class Philosopher extends Thread {

    private Chopstick left;
    private Chopstick right;
    private Random random;

    public Philosopher(Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                //思考一段时间
                Thread.sleep(random.nextInt(1000));
                synchronized (left) {
                    synchronized (right) {
                        //进餐一段时间
                        Thread.sleep(random.nextInt(1000));
                        System.out.println("Philosopher " + this + " eating");
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}