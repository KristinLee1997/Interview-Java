package com.kristin.algorithm.sort.insert;

import com.kristin.algorithm.sort.TestHelper;

/**
 * @author Kristin
 * @since 2018/9/2 9:24
 **/
public class InsertSort {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 3, 9, 4, 8, 7, 2, 6};
        sort(arr);
        TestHelper.printArr(arr);
    }
}
