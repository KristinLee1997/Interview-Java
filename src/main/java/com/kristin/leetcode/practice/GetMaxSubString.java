package com.kristin.leetcode.practice;

/**
 * @author Kristin
 * @since 2018/9/7 22:47
 **/
public class GetMaxSubString {
    public static void main(String[] args) {
        String s1 = "asdfitcastghjfghjk";
        String s2 = "xcitcastvbnm";
        String res = getSubString(s1, s2);
        System.out.println(res);
    }

    public static String getSubString(String str1, String str2) {
        String max = str1.length() > str2.length() ? str1 : str2;
        String min = max.equals(str1) ? str2 : str1;
        for (int i = 0; i < min.length(); i++) {
            for (int start = 0, end = min.length() - i; end < min.length(); start++, end++) {
                String temp = min.substring(start, end);
                if (max.contains(temp)) {
                    return temp;
                }
            }
        }
        return null;
    }
}
