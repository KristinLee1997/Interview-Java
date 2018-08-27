package com.kristin.笔试题.nowcoder.haoweilai;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Kristin
 * @since 2018/8/25 9:59
 * 输入n个整数,返回数组中出现次数大于n/2的元素
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        String[] arr = str.split(" ");
        int[] num = new int[arr.length];
        for (int i = 0; i < num.length; i++) {
            num[i] = Integer.parseInt(arr[i]);
        }
        int res = getNum(num);
        System.out.println(res);
    }

    public static int getNum(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == null) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            if ((Integer) entry.getValue() >= arr.length / 2) {
                return (Integer) entry.getKey();
            }
        }
        return -1;
    }
}
