package com.xgg.hightconcurren.jvm;

public class IntegerValue {
    //int 类型在全局变量中不显示赋值，会默认赋值为 0
    int i;
    public static void main(String[] args) {
        //int 类型在局部变量中不显示的赋值，编译通不过
//        int i;
//        System.out.println(i);
    }
}
