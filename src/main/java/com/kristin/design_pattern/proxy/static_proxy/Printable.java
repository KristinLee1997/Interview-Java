package com.kristin.design_pattern.proxy.static_proxy;

/**
 * @author Kristin
 * @since 2018/7/27 10:47
 **/
public interface Printable {
    void setPrinterName(String name);

    String getPrinterName();

    void print(String message);
}
