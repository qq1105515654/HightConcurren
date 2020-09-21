package com.xgg.hightconcurren.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/4 10:27
 * @description TODO
 **/
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ShutDownTask());
        }
//        executorService.shutdown();
        Thread.sleep(1500);
        List<Runnable> runnables = executorService.shutdownNow();
//        executorService.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被停止了");
        }

    }
}
