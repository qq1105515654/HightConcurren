package com.xgg.hightconcurren.jvm;

import lombok.Data;

@Data
public class User {

    private String name;

    private Integer age;

    private String addr;

    private Object obj;

    private short s;

    private byte b;

    private void print(){
        System.out.println("========================另一个版本的，打破双亲委派的机制========================");
    }
}
