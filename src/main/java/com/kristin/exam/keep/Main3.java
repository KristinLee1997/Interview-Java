package com.kristin.exam.keep;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        int[] num = new int[100];
        int[] table = new int[65535];
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            for (int i = 0; scan.hasNext(); i++) {
                num[i] = scan.nextInt();
            }
            for (int i = 0; i < num.length; i++) {
                table[i]++;
            }
            for (int i = 0; i < table.length; i++) {
                if (table[i] >= num.length / 2) {
                    System.out.println(i);
                }
            }
        }
    }
}
