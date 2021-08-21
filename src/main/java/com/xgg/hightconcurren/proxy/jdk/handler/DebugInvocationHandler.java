package com.xgg.hightconcurren.proxy.jdk.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DebugInvocationHandler implements InvocationHandler {

    //代理类中真实的对象
    private final Object target;

    public DebugInvocationHandler(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method "+method.getName());
        Object result = method.invoke(target, args);
        System.out.println("After method "+method.getName());
        return result;
    }
}
