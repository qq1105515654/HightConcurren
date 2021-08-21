package com.xgg.hightconcurren.proxy.cglib.factory;

import com.xgg.hightconcurren.proxy.cglib.interceptor.DebugMethodInterceptor;
import com.xgg.hightconcurren.proxy.jdk.handler.DebugInvocationHandler;
import net.sf.cglib.proxy.Enhancer;

public class CglibProxyFactory {

    public static Object getProxy(Class<?> clazz){
        Enhancer enhancer=new Enhancer();
        //设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        //设置被代理的类
        enhancer.setSuperclass(clazz);
        //设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());
        //创建代理类
        return enhancer.create();
    }
}
