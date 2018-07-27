package com.kristin.design_pattern.proxy.static_proxy;

/**
 * @author Kristin
 * @since 2018/7/27 10:53
 **/
public class PrinterProxy implements Printable {
    private String name;
    private Printer real;

    public PrinterProxy(String name) {
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return this.name;
    }

    @Override
    public synchronized void setPrinterName(String name) {
        if (real != null) {
            real.setPrinterName(name);
        }
        this.name = name;
    }

    @Override
    public void print(String message) {
        realize();
        real.print(message);
    }

    public synchronized void realize() {
        if (real == null) {
            real = new Printer(name);
        }
    }
}
