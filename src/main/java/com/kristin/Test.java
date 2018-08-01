package com.kristin;

import com.kristin.java_base.java_collection.util.MyHashMap;

/**
 * @author Kristin
 * @since 2018/7/27 23:09
 **/
class Test {
    @org.junit.jupiter.api.Test
    void test() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, "a");
        hashMap.put(3, "v");
        System.out.println(hashMap);
    }
}
