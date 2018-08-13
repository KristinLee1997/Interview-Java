package com.kristin.java_multithread.testvolatile;

import org.junit.jupiter.api.Test;

/**
 * @author Kristin
 * @since 2018/8/13 15:37
 * 下载hsfis插件: https://sourceforge.net/projects/fcml/files/fcml-1.1.1/hsdis-1.1.1-win32-amd64.zip/download
 * 将插件放到Java\jdk1.8.0_121\jre\bin\server\目录下
 * 配置VM : -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*TestVolatile.test
 **/
public class TestVolatile {
    public volatile Singleton singleton;

    @Test
    public void test() {
        singleton = new Singleton();
    }
}

class Singleton {

}
