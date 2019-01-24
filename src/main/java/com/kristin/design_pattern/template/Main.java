package com.kristin.design_pattern.template;

public class Main {
    public static void main(String[] args) {
        AbstractdDisplay charDisplay = new CharDisplay('H');
        AbstractdDisplay stringDisplay = new StringDisplay("hello");
        AbstractdDisplay chStringDisplay = new StringDisplay("你好");
        charDisplay.display();
        stringDisplay.display();
        chStringDisplay.display();
    }
}
