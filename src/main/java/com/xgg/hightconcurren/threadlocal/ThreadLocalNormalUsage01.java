package com.xgg.hightconcurren.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/8 9:50
 * @description TODO
 **/
public class ThreadLocalNormalUsage01 {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println(new ThreadLocalNormalUsage00().date(finalI));
            }).start();
            Thread.sleep(500);
        }

    }

    public String date(int seconds){
        Date date=new Date(1000*seconds);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }
}
