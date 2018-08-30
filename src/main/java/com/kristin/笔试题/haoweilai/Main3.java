package com.kristin.笔试题.haoweilai;

import java.util.Scanner;

/**
 * @author Kristin
 * @since 2018/8/28 18:57
 **/
public class Main3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] num = new int[8];
        for (int i = 0; i < 8; i++) {
            num[i] = scan.nextInt();
        }
        int res = getSum(num, 8);
        System.out.println(res);
    }

    public static int getSum(int[] num, int n) {
        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = num[0];
        int count = 1;
        for (int i = 1; i < n; i++) {

        }
        return 0;
    }
}
