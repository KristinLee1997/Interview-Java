package com.kristin.笔试题.nowcoder;

/**
 * @author Kristin
 * @since 2018/8/29 21:44
 **/
public class BusTime {
    public static void main(String[] args) {
        int[] stops = {13, 15, 26, 7, 27, 3, 30};
        int[] persiod = {1, 2, 1, 2, 2, 2, 1};
        int[] interval = {5, 1, 4, 3, 2, 1, 4};
        int n = 7;
        int s = 10;
        System.out.println(new BusTime().chooseLine(stops, persiod, interval, n, s));
    }

    // 停站数stops,停站时间period,发车间隔interval及公交路数n，出发时间s。
    public int chooseLine(int[] stops, int[] period, int[] interval, int n, int s) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int wait = s % interval[i] == 0 ? 0 : interval[i] - (s % interval[i]);
            int lucheng = 5 * (stops[i] + 1) + stops[i] * period[i];
            sum = s + wait + lucheng;
            min = sum < min ? sum : min;
        }
        return min;
    }
}
