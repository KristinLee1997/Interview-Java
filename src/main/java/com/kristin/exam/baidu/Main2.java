package com.kristin.exam.baidu;

import java.util.Scanner;

/**
 * @author Kristin
 * @since 2018/9/1 13:12
 **/
public class Main2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length - 1; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length - 1; j++) {
                sum += Math.abs(nums[j + 1] - nums[j]);
            }
            sum = sum - Math.abs(nums[i + 1] - nums[i]) - Math.abs(nums[i] - nums[i - 1]) + Math.abs(nums[i + 1] - nums[i - 1]);
            if (sum < min) {
                min = sum;
            }
        }
        System.out.println(min);
    }
}
