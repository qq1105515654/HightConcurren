package com.xgg.hightconcurren.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/4 10:14
 * @description TODO
 **/
public class WorkStealingPoolTest {


    public static void main(String[] args) {
        Executors.newWorkStealingPool();
    }
}
