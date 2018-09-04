package com.kristin.leetcode.动态规划.整数拆分;

/**
 * @author Kristin
 * @since 2018/8/31 8:56
 **/
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().integerBreak(6));
    }

    public int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] * 2, dp[i - 3] * 3);
        }
        return dp[n];
    }
}