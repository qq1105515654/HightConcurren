package com.xgg.hightconcurren.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/3 15:41
 * @description TODO
 **/
public class FixedThreadPoolOOM {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new FixedThreadPoolOOM.Task());
        }
    }

    static final class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}


