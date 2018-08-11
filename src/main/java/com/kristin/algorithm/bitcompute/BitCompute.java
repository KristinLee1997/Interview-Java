package com.kristin.algorithm.bitcompute;

/**
 * @author Kristin
 * @since 2018/8/11 7:07
 **/
public class BitCompute {
    public void exg(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + " " + b);
    }


    public int getMax(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;
        int sameSab = flip(difSab);
        return a * (difSab * sa + sameSab * sc) + b * flip(difSab * sa + sameSab * sc);
    }

    /**
     * sign函数的功能是返回整数n的符号
     *
     * @return
     */
    private int sign(int n) {
        return flip(n >>> 31) & 1;
    }

    /**
     * flip函数功能是如果 n 为 1 ,返回 0; 如果 n 为 0,返回 1
     *
     * @param n
     * @return
     */
    private int flip(int n) {
        return n ^ 1;
    }

    public static void main(String[] args) {
        BitCompute bitCompute = new BitCompute();
        int a = -5;
        int b = -7;
        int res = bitCompute.getMax(a, b);
        System.out.println(res);
    }
}
