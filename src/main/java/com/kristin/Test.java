package com.kristin;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Kristin
 * @since 2018/7/27 23:09
 **/

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine().toString();
        String str2 = br.readLine().toString();
        String res = get(str1, str2);
        System.out.println(res);
    }

    public static String get(String str1, String str2) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);
            if (str2.indexOf(c) == -1) {
                res.append(c);
            }
        }
        return res.toString();
    }
}