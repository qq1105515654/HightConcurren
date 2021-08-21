package com.xgg.hightconcurren.proxy.cglib;

public class AliSmsService {

    public String send(String message){
        System.out.println("Send message "+message);
        return message;
    }
}
