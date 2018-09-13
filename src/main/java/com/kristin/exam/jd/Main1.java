package com.kristin.exam.jd;

import java.util.Scanner;

/**
 * @author Kristin
 * @since 2018/9/9 18:14
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] num = new int[n][4];
        for (int i = 0; i < n; i++) {
            num[i][0] = scan.nextInt();
            num[i][1] = scan.nextInt();
            num[i][2] = scan.nextInt();
            num[i][3] = 0;
        }
        int count = 0;
        int[][] arr = getNum(num);
        for (int i = 0; i < n; i++) {
            if (arr[i][3] == 1) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static int[][] getNum(int[][] num) {
        int count = 0;
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length; j++) {
                if (num[i][0] < num[j][0]) {
                    if (num[i][1] < num[j][1] && num[i][2] < num[j][2]) {
                        num[i][3] = 1;
                        continue;
                    }
                }
            }
        }
        return num;
    }
}
