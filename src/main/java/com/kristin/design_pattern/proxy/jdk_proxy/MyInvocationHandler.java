package com.kristin.design_pattern.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Kristin
 * @since 2018/7/27 11:21
 **/

/**
 * 运行时生成代理类的Handler
 */
public class MyInvocationHandler implements InvocationHandler {
    /**
     * 真实类对象
     */
    private Object target;

    /**
     * 将真实类的实例对象设置到自定义InvocationHandler中
     *
     * @param target
     */
    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 运行时生成真实类的代理类
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-------------------before------------------");
        Object result = method.invoke(target, args);
        System.out.println("--------------------after-------------------");
        return result;
    }
}
