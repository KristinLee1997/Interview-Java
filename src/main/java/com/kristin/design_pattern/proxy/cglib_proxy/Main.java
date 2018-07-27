package com.kristin.design_pattern.proxy.cglib_proxy;

import org.junit.jupiter.api.Test;

/**
 * @author Kristin
 * @since 2018/7/27 17:34
 **/
public class Main {
    //实例化一个DynamicProxy代理对象
    //通过getProxyObject方法获得被代理后的对象
    Math math = (Math) new DynamicProxy().getProxyObject(new Math());

    @Test
    public void test() {
        int n1 = 100, n2 = 5;
        math.add(n1, n2);
        math.sub(n1, n2);
        math.mut(n1, n2);
        math.div(n1, n2);
    }
}
