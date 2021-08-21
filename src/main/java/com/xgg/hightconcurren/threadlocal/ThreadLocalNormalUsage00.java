package com.xgg.hightconcurren.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/7 17:23
 * @description TODO
 **/
public class ThreadLocalNormalUsage00 {

    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            System.out.println(new ThreadLocalNormalUsage00().date(10));
        });
        t1.start();
        Thread t2=new Thread(() ->{
            System.out.println(new ThreadLocalNormalUsage00().date(1000));
        });
        t2.start();

    }

    public String date(int seconds){
        Date date=new Date(1000*seconds);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }


}
