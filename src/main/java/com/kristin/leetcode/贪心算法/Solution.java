package com.kristin.leetcode.贪心算法;

/**
 * @author Kristin
 * @since 2018/9/3 18:18
 **/
public class Solution {
    public static boolean lemonadeChange(int[] bills) {
        int[] money = new int[3];
        for (int i = 0; i < bills.length; i++) {
            if (!getMoney(bills[i], money)) {
                System.out.println(i);
                for(int j=0;j<money.length;j++){
                    System.out.print(money[j]+" ");
                }
                return false;
            }
        }
        return true;
    }

    public static boolean getMoney(int dollar, int[] money) {
        if (dollar == 5) {
            money[0]++;
            return true;
        } else {
            if (dollar == 10) {
                money[1]++;
                if (money[0] >= 1) {
                    money[0]--;
                    return true;
                } else {
                    return false;
                }
            } else {
                money[2]++;
                if (money[0] >= 1 && money[1] >= 1) {
                    money[0]--;
                    money[1]--;
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 5, 5, 20, 5, 20, 5};
        System.out.println(lemonadeChange(arr));
    }
}
