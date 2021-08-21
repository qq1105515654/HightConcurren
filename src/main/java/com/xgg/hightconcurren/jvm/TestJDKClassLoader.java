package com.xgg.hightconcurren.jvm;

import sun.misc.Launcher;

import java.net.URL;

public class TestJDKClassLoader {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
        System.out.println(TestJDKClassLoader.class.getClassLoader());

        System.out.println();

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapLoader= extClassLoader.getParent();
        System.out.println("The bootstrapClassLoader ："+bootstrapLoader);
        System.out.println("The extClassLoader ："+extClassLoader);
        System.out.println("The appClassLoader ："+appClassLoader);

        System.out.println();

        System.out.println("bootstrapClassLoader加载以下文件：");
        URL[] urls= Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }

        System.out.println();
        System.out.println("extClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));
    }
}
