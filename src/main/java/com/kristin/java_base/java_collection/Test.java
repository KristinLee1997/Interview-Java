package com.kristin.java_base.java_collection;

import com.kristin.java_base.java_collection.util.MyHashMap;

/**
 * @author Kristin
 * @since 2018/7/27 23:09
 **/
public class Test {
    @org.junit.jupiter.api.Test
    public void test() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, "a");
        hashMap.put(4, "d");
        hashMap.put(3, "c");
        hashMap.put(2, "b");
        System.out.println(hashMap);

    }
}
