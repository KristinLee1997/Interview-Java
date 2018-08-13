package com.kristin.笔试题.网易;

import java.util.Scanner;

/**
 * @author Kristin
 * @since 2018/8/11 16:13
 **/
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[] hobby = new int[n];
        for (int i = 0; i < n; i++) {
            hobby[i] = scan.nextInt();
        }
        int[] flag = new int[n];
        for (int i = 0; i < n; i++) {
            flag[i] = scan.nextInt();
        }
        if (k > n) {
            return;
        }
        int total = 0;
        int max = 0;
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (flag[i] == 0) {
                int temp = max;
                int sum = compute(hobby, n, i, k);
                max = sum > max ? sum : max;
                if (max != temp) {
                    pos = i;
                }
            }
        }
        total = max;
        for (int i = 0; i < pos; i++) {
            if (flag[i] == 1) {
                total += hobby[i];
            }
        }
        System.out.println(total);
    }

    public static int compute(int[] hobby, int n, int start, int k) {
        int sum = 0;
        for (int i = start,pos = 0; pos < k && k < n && i < n; i++, pos++) {
            sum += hobby[i];
        }
        return sum;
    }
}
