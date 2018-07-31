package com.kristin.java_base.java_collection.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author Kristin
 * @since 2018/7/31 14:14
 **/
public class ArrayListTest {

    @Test
    public void getSize() {
        MyArrayList arrayList = new MyArrayList(13);
        System.out.println(arrayList.size());
        for (int i = 0; i < 14; i++) {
            arrayList.add(i + 1);
        }
        System.out.println(arrayList.size());
        arrayList.ensureCapacity(20);
        System.out.println(arrayList.size());
        try {
            Object obj = Class.forName("com.kristin.java_base.java_collection.util.MyArrayList");
            Field fields = MyArrayList.class.getDeclaredField("DEFAULT_CAPACITY");
            fields.setAccessible(true);
            System.out.println(fields.get(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        MyArrayList myArrayList = new MyArrayList(5);
        System.out.println();
        myArrayList.add(1);

    }
}
