package com.kristin.design_pattern.proxy.jdk_proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author Kristin
 * @since 2018/7/27 11:24
 **/
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        UserDao user = new UserDaoImpl();     //实例化真实类
        MyInvocationHandler handler = new MyInvocationHandler(user);
        //保存class文件到本地,可以在项目根目录下的com.sun.proxy文件夹中看到$Proxy.class
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //运行时生成代理类
        /**
         * 方法一:
         */
        Class classProxy = Proxy.getProxyClass(user.getClass().getClassLoader(), UserDao.class);
        Constructor constructor = classProxy.getConstructor(InvocationHandler.class);
        UserDao userDao = (UserDao) constructor.newInstance(new MyInvocationHandler(user));
        userDao.add();

        /**
         * 方法二
         */
//        UserDao userDao = (UserDao) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(), handler);
//        UserDao userDao = (UserDao) Proxy.newProxyInstance(UserDao.class.getClassLoader(), new Class[]{UserDao.class}, handler);
//        userDao.add();  //调用代理类的方法


    }
}
