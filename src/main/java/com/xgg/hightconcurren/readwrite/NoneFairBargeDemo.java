package com.xgg.hightconcurren.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/14 17:52
 * @description TODO 演示非公平读写锁的时候，读锁插队的情况
 **/
public class NoneFairBargeDemo {

    public static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock(false);

    public static ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();

    public static ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();

    public static void main(String[] args) {
        new Thread(() -> write()).start();
        new Thread(() -> read()).start();
        new Thread(() -> read()).start();
        new Thread(() -> write()).start();
        new Thread(() -> read()).start();
        new Thread(() -> read()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread[] threads=new Thread[1000];
                for (int i = 0; i < 1000; i++) {
                    threads[i]=new Thread(()->read(),"子线程创建的任务线程"+i);
                }
                for (Thread thread : threads) {
                    thread.start();
                }
            }
        }).start();
//TODO      在非公平锁的情况下：第一步写锁获取到锁，写锁是排它的。所以其他的线程只能等待。
//TODO      第二步：当写锁释放的一瞬间，这个时候子线程刚好在获取读锁，读锁是非互斥锁。刚好在任务队列中前面的也是读锁线程。所以这个时候就发生了读锁插队。
    }


    public static void read(){
        System.out.println(Thread.currentThread().getName()+"：读锁开始尝试获取锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"：读锁获取了锁");
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"：读锁释放了锁");
            readLock.unlock();
        }
    }

    public static void write(){
        System.out.println(Thread.currentThread().getName()+"：写锁开始尝试获取锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"：写锁获取了锁");
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"：写锁释放了锁");
            writeLock.unlock();
        }
    }

}