package com.xgg.hightconcurren.proxy.cglib;

import com.xgg.hightconcurren.proxy.cglib.factory.CglibProxyFactory;

public class TestCglibProxy {

    public static void main(String[] args) {
        AliSmsService aliSmsService= (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("Java");
    }
}
