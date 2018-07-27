package com.kristin.design_pattern.proxy.cglib_proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Kristin
 * @since 2018/7/27 18:34
 **/
public class Interceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("-------------------before------------------");
        proxy.invokeSuper(obj, args);
        System.out.println("--------------------after-------------------");
        return null;
    }
}
