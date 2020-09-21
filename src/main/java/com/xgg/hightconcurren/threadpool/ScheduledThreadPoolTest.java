package com.xgg.hightconcurren.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/3 16:28
 * @description TODO
 **/
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(new Task(),5L, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Task(),1,3,TimeUnit.SECONDS);
    }
}
