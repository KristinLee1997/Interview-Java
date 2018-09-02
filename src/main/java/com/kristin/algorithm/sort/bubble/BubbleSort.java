package com.kristin.algorithm.sort.bubble;

import com.kristin.algorithm.sort.TestHelper;

/**
 * @author Kristin
 * @since 2018/9/2 9:06
 **/
public class BubbleSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int flag = 1;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    TestHelper.swap(arr, j, j + 1);
                    flag = 0;
                }
            }
            if (flag == 1) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 3, 9, 4, 8, 7, 2, 6};
        sort(arr);
        TestHelper.printArr(arr);
    }
}
