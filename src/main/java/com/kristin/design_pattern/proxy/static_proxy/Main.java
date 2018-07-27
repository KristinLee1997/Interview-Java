package com.kristin.design_pattern.proxy.static_proxy;

/**
 * @author Kristin
 * @since 2018/7/27 10:39
 **/
public class Main {
    public static void main(String[] args) {
        Printable p = new PrinterProxy("kristin");
        System.out.println("name: " + p.getPrinterName());
        p.setPrinterName("kkk");
        System.out.println("new name: " + p.getPrinterName());
        p.print("hello world");
    }
}
