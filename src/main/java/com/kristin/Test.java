package com.kristin;

/**
 * @author Kristin
 * @since 2018/7/27 23:09
 **/
public class Test {
    public volatile Singleton singleton;

    @org.junit.jupiter.api.Test
    public void test() {
        singleton = new Singleton();
    }
}

class Singleton {

}