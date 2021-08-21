package com.xgg.hightconcurren.jvm;

public class JavaMemoryModel {

    public static final String machine="Memory";

    public static int math=10;

    public static void main(String[] args) {

        JavaMemoryModel jmm = new JavaMemoryModel();
        jmm.compute();

    }

    public int compute(){
        int a=1;
        int b=2;
        int c=(a+b) * 10;
        return c;
    }
}
