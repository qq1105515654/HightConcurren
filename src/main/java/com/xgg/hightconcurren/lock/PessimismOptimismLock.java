package com.xgg.hightconcurren.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/11 10:57
 * @description TODO pessimism 悲观   optimism 乐观
 **/
public class PessimismOptimismLock {



    int a=0;

    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(1);
        int i = atomicInteger.incrementAndGet();
        System.out.println(i);
    }

    /**
     * @author snh
     * @date 11:13 2020/9/
     * @param
     * @return void
     * @Description TODO
     **/
    public synchronized void testMethod(){
        a++;
    }
}
