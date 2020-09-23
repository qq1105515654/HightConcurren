package com.xgg.hightconcurren.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/23 15:46
 * @description TODO 演示 在高并发情况下 LongAdder 的效率比 AtomicLong 要高
 **/
public class AtomicLongDemo {

    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Task(counter));
        }
        executorService.shutdown();
        while(!executorService.isTerminated()){}
        long end = System.currentTimeMillis();
        System.out.println("AtomicLong耗时："+(end-start));
        System.out.println(counter.get());
    }

    private static class Task implements Runnable{
        private AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i <10000 ; i++) {
                counter.getAndIncrement();
            }
        }
    }

}
