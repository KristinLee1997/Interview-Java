package com.kristin.exam.beike;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        String[] sstr = str.split(" ");
        int n = Integer.parseInt(sstr[0]);
        int m = Integer.parseInt(sstr[1]);
        int[] num = new int[31];
        for (int i = 0; i < num.length; i++) {
            num[i] = (int) Math.pow(2, i);
        }
        int count = 0;
        if (m % 2 != 0) {
            m++;
            count++;
        }
        int temp = 0;
        for (int i = 0; i < num.length; i++) {
            if (m / num[i] < n) {
                temp = i;
                m /= num[i];
                break;
            }
        }
        count += temp;
        if (m % 2 != 0) {
            count = count + (n - m);
        }
        System.out.println(count);
    }
}
