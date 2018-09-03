package com.kristin.algorithm.sort.heap;

import com.kristin.algorithm.sort.TestHelper;

/**
 * @author Kristin
 * @since 2018/9/3 16:21
 **/
public class HeapSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = (n - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            TestHelper.swap(arr, 0, i);
            shiftDown(arr, i, 0);
        }
    }

    public static void shiftDown(int[] arr, int n, int k) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && arr[j + 1] > arr[j]) {
                j++;
            }
            if (arr[k] >= arr[j]) {
                break;
            }
            TestHelper.swap(arr, k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        int[] arr = {30, 41, 28, 16, 13, 22, 52, 17, 15, 19};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
