package com.kristin.leetcode.动态规划.最小路径和;

/**
 * @author Kristin
 * @since 2018/8/30 22:13
 **/
public class Solution {
    public static void main(String[] args) {
        int[][] num = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(new Solution().minPathSum(num));
    }

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        int sum1 = 0;
        for (int i = 0; i < grid[0].length; i++) {
            sum1 += grid[0][i];
            dp[0][i] = sum1;
        }
        int sum2 = 0;
        for (int i = 0; i < grid.length; i++) {
            sum2 += grid[i][0];
            dp[i][0] = sum2;
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = (dp[i][j - 1] < dp[i - 1][j] ? dp[i][j - 1] : dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
