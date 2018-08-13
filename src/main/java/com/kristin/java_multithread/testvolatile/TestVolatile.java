package com.kristin.java_multithread.testvolatile;

import org.junit.jupiter.api.Test;

/**
 * @author Kristin
 * @since 2018/8/13 15:37
 **/
public class TestVolatile {
    public volatile Singleton singleton;

    @Test
    public void test() {
        singleton = new Singleton();
    }
}

class Singleton {

}
