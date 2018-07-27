package com.kristin.design_pattern.proxy.static_proxy;

/**
 * @author Kristin
 * @since 2018/7/27 10:48
 **/
public class Printer implements Printable {
    private String name;

    public Printer() {
        heavyJob("正在初始化Printer[*.*]");
    }

    public Printer(String name) {
        this.name = name;
        heavyJob("正在初始化Printer-" + this.name + "的实例");
    }

    @Override
    public void setPrinterName(String name) {
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return this.name;
    }

    @Override
    public void print(String message) {
        System.out.println("I am Printer-" + this.name);
        System.out.println("Message : " + message);
    }

    public void heavyJob(String msg) {
        System.out.println(msg);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }
        System.out.println("over");
    }
}
