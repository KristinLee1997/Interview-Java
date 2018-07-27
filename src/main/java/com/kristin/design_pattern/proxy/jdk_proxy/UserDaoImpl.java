package com.kristin.design_pattern.proxy.jdk_proxy;

/**
 * @author Kristin
 * @since 2018/7/27 11:20
 **/

/**
 * 真实类
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("this is method-add running");
    }
}
