package com.xgg.hightconcurren.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/3 16:22
 * @description TODO
 **/
public class CacheThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Task());
        }
    }
}
