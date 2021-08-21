package com.xgg.hightconcurren.proxy.cglib.interceptor;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DebugMethodInterceptor implements MethodInterceptor {

    /**
     *
     * @param o             被代理的对象
     * @param method        被拦截的方法（需要增强的方法）
     * @param args          方法入参列表
     * @param methodProxy   用于调用原始方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before method "+method.getName());
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("After method "+method.getName());
        return result;
    }
}
