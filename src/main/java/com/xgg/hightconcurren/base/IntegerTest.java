package com.xgg.hightconcurren.base;

public class IntegerTest {


    public static void main(String[] args) {
        byte a=127;
        byte b=127;

        a+=b;

        System.out.println(a);

    }
}
