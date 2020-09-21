package com.xgg.hightconcurren.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/14 12:00
 * @description TODO 可重入锁演示
 **/
public class ReentrantLocked implements Runnable{

    public static void main(String[] args) {

        new Thread(new ReentrantLocked()).start();
        new Thread(new ReentrantLocked()).start();
        new Thread(new ReentrantLocked()).start();

    }

    public static ReentrantLock lock=new ReentrantLock();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"：开始抢票");
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"：抢到票了");
        }finally {
            lock.unlock();
        }
    }
}
