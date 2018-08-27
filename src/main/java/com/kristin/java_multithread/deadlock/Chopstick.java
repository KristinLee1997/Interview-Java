package com.kristin.java_multithread.deadlock;

/**
 * @author Kristin
 * @since 2018/8/27 10:06
 * 筷子
 **/
public class Chopstick {
    public final int id;
    private Status status = Status.PUT_DOWN;

    public static enum Status {
        PICKED_UP,
        PUT_DOWN
    }

    public Chopstick(int id) {
        super();
        this.id = id;
    }

    public void pickUp() {
        status = Status.PICKED_UP;
    }

    public void putDown() {
        status = Status.PUT_DOWN;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "chopstick-" + id;
    }
}
