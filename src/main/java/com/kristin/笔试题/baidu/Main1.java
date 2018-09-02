package com.kristin.笔试题.baidu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Kristin
 * @since 2018/9/1 12:33
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] price = new int[n];
        for (int i = 0; i < n; i++) {
            price[i] = scan.nextInt();
        }
        Arrays.sort(price);
        int flag = 1;
        int res = 0;
        for (int i = 0; i < price.length - 1; i++) {
            if (flag == 3) {
                System.out.println(res);
                return;
            }
            if (price[i] < price[i + 1]) {
                flag++;
                res = price[i + 1];
                continue;
            }
        }
        if (flag == 3) {
            System.out.println(res);
            return;
        }
        if (flag != 3) {
            System.out.println(-1);
        }
    }
}