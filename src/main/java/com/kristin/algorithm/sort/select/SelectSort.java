package com.kristin.algorithm.sort.select;

import com.kristin.algorithm.sort.TestHelper;

/**
 * @author Kristin
 * @since 2018/9/2 11:48
 **/
public class SelectSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            TestHelper.swap(arr, min, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 3, 9, 4, 8, 7, 2, 6};
        sort(arr);
        TestHelper.printArr(arr);
    }
}
