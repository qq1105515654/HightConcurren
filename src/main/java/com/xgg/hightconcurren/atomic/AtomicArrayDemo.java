package com.xgg.hightconcurren.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/23 11:29
 * @description TODO
 **/
public class AtomicArrayDemo implements Runnable {

    public static AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(1000);


    public static void main(String[] args) throws InterruptedException {
        AtomicArrayDemo atomicArrayDemo = new AtomicArrayDemo();
        Thread t1=new Thread(atomicArrayDemo);
        Thread t2=new Thread(atomicArrayDemo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println("第"+i+"个值："+atomicIntegerArray.get(i));
        }
    }


    public static void decrement(){
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.getAndDecrement(i);
        }
    }

    public static void increment(){
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.getAndIncrement(i);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            increment();
            decrement();
        }
    }
}

