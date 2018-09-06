package com.kristin.exam.nowcoder;

import java.util.Scanner;

/**
 * @author Kristin
 * @since 2018/8/29 21:45
 **/
public class FenPanZI {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int m = scan.nextInt();
            int n = scan.nextInt();
            System.out.println(getNum(m, n));
        }
    }

    public static int getNum(int m, int n) {
        if (m == 0 || n == 1) {
            return 1;
        }
        if (m < n) {
            return getNum(m, m);
        }
        return getNum(m - n, n) + getNum(m, n - 1);
    }
}
