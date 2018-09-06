package com.kristin.exam.beike;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] numa = new int[n];
        int[] demo = new int[n];
        Map map = new HashMap<>();
        Map shunxu = new HashMap();
        for (int i = 0; i < n; i++) {
            int a = scan.nextInt();
            numa[i] = a;
            demo[i] = a;
            int b = scan.nextInt();
            map.put(a, b);
            shunxu.put(a, i + 1);
        }
        Arrays.sort(numa);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = (int) map.get(numa[i]);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(getPos(res, i) + " ");
        }
        System.out.println();
    }

    public static int getPos(int[] arr, int pos) {
        int flag = pos;
        for (int i = pos; i < arr.length; i++) {
            if (arr[i] <= arr[pos]) {
                flag++;
            } else {
                break;
            }
        }
        return (flag - pos);
    }
}
