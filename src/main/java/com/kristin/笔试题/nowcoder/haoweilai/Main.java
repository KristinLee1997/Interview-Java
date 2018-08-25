package com.kristin.笔试题.nowcoder.haoweilai;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 按照升序输出n个数中最小的k个数
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        String[] strArr = str.split(" ");
        int k = Integer.parseInt(strArr[strArr.length - 1]);
        int[] arr = new int[strArr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }
        int[] heap = getKMin(arr, k);
        Arrays.sort(heap);
        for (int i = 0; i < k - 1; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println(heap[k - 1]);
    }

    public static int[] getKMin(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int[] kHeap = new int[k];
        for (int i = 0; i != k; i++) {
            heapInsert(kHeap, arr[i], i);
        }
        for (int i = k; i != arr.length; i++) {
            if (arr[i] < kHeap[0]) {
                kHeap[0] = arr[i];
                heapify(kHeap, 0, k);
            }
        }
        return kHeap;
    }

    public static void heapInsert(int[] arr, int value, int index) {
        arr[index] = value;
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (arr[parent] < arr[index]) {
                swap(arr, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;
        while (left < heapSize) {
            if (arr[left] > arr[index]) {
                largest = left;
            }
            if (right < heapSize && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest != index) {
                swap(arr, largest, index);
            } else {
                break;
            }
            index = largest;
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}