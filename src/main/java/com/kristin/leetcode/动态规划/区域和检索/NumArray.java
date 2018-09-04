package com.kristin.leetcode.动态规划.区域和检索;

/**
 * @author Kristin
 * @since 2018/8/30 22:40
 **/
class NumArray {
    private int[] sums;

    public NumArray(int[] nums) {
        this.sums = nums;
        for (int k = 1; k < nums.length; k++) {
            this.sums[k] += this.sums[k - 1];
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums);
        int res = obj.sumRange(0, 2);
        System.out.println(res);
    }

    public int sumRange(int i, int j) {
        return i == 0 ? this.sums[j] : (this.sums[j] - this.sums[i - 1]);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
