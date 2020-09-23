package com.xgg.hightconcurren.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/23 11:54
 * @description TODO 演示将 int 类型的普通变量升级为原子变量
 **/
public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    public static AtomicIntegerFieldUpdater<Candidate> aif=AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");


    static Candidate tom=new Candidate();

    static Candidate peter=new Candidate();

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerFieldUpdaterDemo fieldUpdaterDemo=new AtomicIntegerFieldUpdaterDemo();
        Thread t1=new Thread(fieldUpdaterDemo);
        Thread t2 = new Thread(fieldUpdaterDemo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Tom："+tom.score);
        System.out.println("Peter："+peter.score);
    }

    @Override
    public void run() {

        for (int i = 0; i < 100000; i++) {
            tom.score++;
            aif.getAndIncrement(peter);
        }
    }


    public static class Candidate{
         volatile int score;
    }
}
