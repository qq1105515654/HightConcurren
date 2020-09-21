package com.xgg.hightconcurren.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/8 9:57
 * @description TODO 利用 ThreadLocal 来为10个线程创建属于自己的 SimpleDateFormat对象，即保证了线程安全问题，又解决了任务过多的资源消耗问题。
 **/
public class ThreadLocalNormalUsage05 {

    public static ExecutorService executorService= Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(new ThreadLocalNormalUsage05().date(finalI));
                }
            });
        }
        executorService.shutdown();
    }


    public String date(int seconds){
        Date date=new Date(1000*seconds);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf=ThreadLocalFormatter.threadLocalFormat.get();
        return sdf.format(date);
    }
}

class ThreadLocalFormatter{

    public static ThreadLocal<SimpleDateFormat> threadLocalFormat=new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };

    /**
     * @author snh
     * @date 11:08 2020/9/8
     * @param null
     * @return
     * @Description TODO 使用
     **/
    public static ThreadLocal<SimpleDateFormat> threadLocalFormat2=ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

}