package com.kristin.design_pattern.proxy.jdk_proxy;

import java.lang.reflect.Proxy;

/**
 * @author Kristin
 * @since 2018/7/27 11:24
 **/
public class Main {
    public static void main(String[] args) {
        UserDao user = new UserDaoImpl();     //实例化真实类
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.setProxy(user);
        //运行时生成代理类
//        UserDao userDao = (UserDao) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(), handler);
        UserDao userDao = (UserDao) Proxy.newProxyInstance(UserDao.class.getClassLoader(), new Class[]{UserDao.class}, handler);
        userDao.add();  //调用代理类的方法
    }
}
