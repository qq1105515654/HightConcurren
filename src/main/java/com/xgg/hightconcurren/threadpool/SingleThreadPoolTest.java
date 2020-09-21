package com.xgg.hightconcurren.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/3 15:53
 * @description TODO
 **/
public class SingleThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i <10000 ; i++) {
            executorService.execute(new Task());
        }
    }
}
