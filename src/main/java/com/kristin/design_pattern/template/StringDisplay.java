package com.kristin.design_pattern.template;

public class StringDisplay extends AbstractdDisplay {
    private String str;
    private Integer width;

    public StringDisplay(String str) {
        this.str = str;
        this.width = str.getBytes().length;
    }

    @Override
    public void open() {
        printLine();
    }

    @Override
    public void print() {
        System.out.println("|" + str + "|");
    }

    @Override
    public void close() {
        printLine();
    }

    private void printLine() {
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
