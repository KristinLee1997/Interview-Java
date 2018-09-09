package com.kristin.exam.meituan;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Kristin
 * @since 2018/9/6 18:40
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nNum = scan.nextInt();
        int kNum = scan.nextInt();
        int tNum = scan.nextInt();
        int[] arr = new int[nNum];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (j - i + 1 == kNum && getTimes(arr, i, j, tNum)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean getTimes(int[] arr, int l, int r, int tNum) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) != null) {
                int res = map.get(arr[i]) + 1;
                map.put(arr[i], res);
            } else {
                map.put(arr[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() >= tNum) {
                return true;
            }
        }
        return false;
    }
}
