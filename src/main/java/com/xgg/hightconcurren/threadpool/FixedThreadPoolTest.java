package com.xgg.hightconcurren.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/3 11:48
 * @description TODO
 **/
public class FixedThreadPoolTest {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i <Integer.MAX_VALUE ; i++) {
            executorService.execute(new Task());
        }
    }


}
class Task implements Runnable{

    @Override
    public void run() {
        /*Thread.sleep(1000);*/
        System.out.println(Thread.currentThread().getName());

    }
}
