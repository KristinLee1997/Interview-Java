package com.kristin.design_pattern.adapter.classAdapter;

public class Banner {
    private String str;

    public Banner(String str) {
        this.str = str;
    }

    public void showWithParen() {
        System.out.println("(" + this.str + ")");
    }

    public void showWithAster() {
        System.out.println("*" + this.str + "*");
    }
}
