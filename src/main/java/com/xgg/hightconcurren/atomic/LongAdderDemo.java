package com.xgg.hightconcurren.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/23 15:46
 * @description TODO 演示 在高并发情况下 LongAdder 的效率比 AtomicLong 要高
 **/
public class LongAdderDemo {

    public static void main(String[] args) {
        LongAdder counter = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Task(counter));
        }
        executorService.shutdown();
        while(!executorService.isTerminated()){}
        long end = System.currentTimeMillis();
        System.out.println("LongAdder耗时："+(end-start));
        System.out.println(counter.sum());
    }

    private static class Task implements Runnable{
        private LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i <10000 ; i++) {
                counter.increment();
            }
        }
    }

}
