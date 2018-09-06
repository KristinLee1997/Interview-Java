package com.kristin.exam.keep;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] num = new int[1000];
        while (scan.hasNext()) {
            String str = scan.nextLine();
            String[] strArr = str.split(" ");
            boolean flag = true;
            for (int i = 0; i < strArr.length; i++) {
                int startTime = Integer.parseInt(strArr[i].split(",")[0]);
                int endTime = Integer.parseInt(strArr[i].split(",")[1]);
                for (int j = startTime; j < endTime; j++) {
                    if (num[j] == 0) {
                        num[j] = 1;
                    } else {
                        flag = false;
                    }
                }
            }
            if (!flag) {
                System.out.println("false");
            } else {
                System.out.println("true");
            }
        }
    }
}

