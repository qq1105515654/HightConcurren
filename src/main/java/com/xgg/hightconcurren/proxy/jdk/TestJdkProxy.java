package com.xgg.hightconcurren.proxy.jdk;

import com.xgg.hightconcurren.proxy.jdk.factory.JdkProxyFactory;

public class TestJdkProxy {


    public static void main(String[] args) {
        SmsService smsService= (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("Java");
    }
}
