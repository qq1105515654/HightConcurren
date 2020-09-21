package com.xgg.hightconcurren.threadlocal;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/9 16:03
 * @description TODO
 **/
public class ThreadLocalNPE {

    public static ThreadLocal<Long> longThreadLocal=new ThreadLocal<>();

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public Long get(){
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE=new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        });
        thread.start();
    }
}
