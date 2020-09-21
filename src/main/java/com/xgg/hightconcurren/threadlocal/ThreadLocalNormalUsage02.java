package com.xgg.hightconcurren.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/8 9:57
 * @description TODO
 **/
public class ThreadLocalNormalUsage02 {

    public static ExecutorService executorService= Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(new ThreadLocalNormalUsage02().date(finalI));
                }
            });
        }
        executorService.shutdown();
    }


    public String date(int seconds){
        Date date=new Date(1000*seconds);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }
}
