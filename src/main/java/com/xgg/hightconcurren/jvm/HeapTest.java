package com.xgg.hightconcurren.jvm;

import java.util.ArrayList;

public class HeapTest {

    public byte[] bytes=new byte[100*1024];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests=new ArrayList<>();

        while(true){
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }

    }
}
