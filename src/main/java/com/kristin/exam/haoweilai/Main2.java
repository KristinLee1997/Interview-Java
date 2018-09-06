package com.kristin.exam.haoweilai;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Kristin
 * @since 2018/8/28 18:27
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        List<Integer> list = new ArrayList<>();

        int up = n > m ? m : n;

        printList(m, up, list);
    }

    public static void printList(int m, int n, List<Integer> list) {
        if (m == 0) {
            System.out.println(list);
            return;
        }

        if (n <= 0 || m < 0) {
            return;
        }

        List list1 = new ArrayList<>(list);
        printList(m, n - 1, list);

        list1.add(n);
        printList(m - n, n - 1, list1);

    }
}