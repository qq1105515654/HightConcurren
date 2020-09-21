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
        new Thread(()->{
            System.out.println(new ThreadLocalNormalUsage00().date(10));
        }).start();

        new Thread(() ->{
            System.out.println(new ThreadLocalNormalUsage00().date(1000));
        }).start();
    }

    public String date(int seconds){
        Date date=new Date(1000*seconds);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }


}
