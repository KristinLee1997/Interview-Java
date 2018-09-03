package com.kristin.algorithm.sort.quick;

import com.kristin.algorithm.sort.TestHelper;

/**
 * @author Kristin
 * @since 2018/9/2 13:10
 **/
public class QucikSort {
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int pos = position(arr, l, r);
        sort(arr, l, pos - 1);
        sort(arr, pos + 1, r);
    }

    private static int position(int[] arr, int l, int r) {
        TestHelper.swap(arr, l, (int) Math.random() * (r - l + 1) + l);
        int temp = arr[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && temp < arr[j]) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && temp > arr[i]) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[j] = temp;
        return j;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 3, 9, 4, 8, 7, 2, 6};
        sort(arr);
        TestHelper.printArr(arr);
    }
}
