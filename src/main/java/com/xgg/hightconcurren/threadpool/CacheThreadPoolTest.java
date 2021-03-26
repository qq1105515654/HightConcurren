package com.xgg.hightconcurren.threadpool;

import org.springframework.util.StringUtils;

import java.util.List;
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

        /*for (int i = 0; i < 1000; i++) {
            executorService.execute(new Task());
        }*/

//        long i = 1 << 30;
//        // 5 * 2³
//        long k = 5 << 3;
//        long j = 1073741824 >> 30;
//
//        int  p = 16 >> 4;
//        System.out.println(i);
//        System.out.println(k);
//        System.out.println(j);
//        System.out.println(p);
    }






}
