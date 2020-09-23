package com.xgg.hightconcurren.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/23 10:23
 * @description TODO 演示原子类跟基础类型 在多线程情况下的线程安全问题
 **/
public class AtomicIntegerDemo1 implements Runnable {

    public static AtomicInteger atomicInteger=new AtomicInteger(0);

    public static int basicCount=0;

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 atomicIntegerDemo1 = new AtomicIntegerDemo1();
        Thread t1=new Thread(atomicIntegerDemo1);
        Thread t2=new Thread(atomicIntegerDemo1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("原子变量自增后的结果："+atomicInteger.get());
        System.out.println("普通变量自增后的结果："+basicCount);

    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    public void incrementAtomic(){
        atomicInteger.getAndIncrement();
    }

    public void incrementBasic(){
        basicCount++;
    }

}
