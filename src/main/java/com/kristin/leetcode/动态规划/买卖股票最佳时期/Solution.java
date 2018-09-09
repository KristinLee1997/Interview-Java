package com.kristin.leetcode.动态规划.买卖股票最佳时期;

/**
 * @author Kristin
 * @since 2018/9/7 12:32
 **/
public class Solution {
    private int[] maxArr;

    public int maxProfit(int[] prices) {
        maxArr = new int[prices.length];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            maxArr[i] = getMax(prices, i);
            int diff = maxArr[i] - prices[i];
            max = diff > 0 ? diff : max;
        }
        return max;
    }

    private int getMax(int[] prices, int index) {
        int max = prices[index];
        for (int i = index + 1; i < prices.length; i++) {
            max = prices[i] > max ? prices[i] : max;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(new Solution().maxProfit(arr));
    }
}
