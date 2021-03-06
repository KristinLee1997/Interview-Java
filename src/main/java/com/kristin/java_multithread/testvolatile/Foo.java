package com.kristin.java_multithread.testvolatile;

/**
 * @author Kristin
 * @since 2018/8/13 15:31
 **/
public class Foo {

    private volatile Bar bar = null;

    public static void main(String[] args) throws Exception {
        System.out.println(new Foo().getBar());
    }

    public Bar getBar() {
        if (bar == null) {
            synchronized (this) {
                if (bar == null) {
                    bar = new Bar();
                }
            }
        }
        return bar;
    }
}

class Bar {

}