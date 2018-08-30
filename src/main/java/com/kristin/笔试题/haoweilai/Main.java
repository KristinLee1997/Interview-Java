package com.kristin.笔试题.haoweilai;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Kristin
 * @since 2018/8/28 16:52
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        for (int j = 0; j < t; j++) {
            String[] str = reader.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            int count = 0;
            for (int i = 1; i < Integer.MAX_VALUE; i++) {
                if ((x + i) == (x | i)) {
                    count++;
                }
                if (count == k) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}