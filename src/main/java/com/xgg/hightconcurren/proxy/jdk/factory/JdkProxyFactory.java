package com.xgg.hightconcurren.proxy.jdk.factory;

import com.xgg.hightconcurren.proxy.jdk.handler.DebugInvocationHandler;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {

    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//目标类的类加载
                target.getClass().getInterfaces(),//目标类实现的接口，可多个
                new DebugInvocationHandler(target)//代理对象对应的自定义 InvocationHandler
        );
    }
}
