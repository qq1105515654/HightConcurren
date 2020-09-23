package com.xgg.hightconcurren.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/14 17:52
 * @description TODO 演示锁的升降级，：锁能降级，但是不能升级
 **/
public class Upgrading {

    public static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock(false);

    public static ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();

    public static ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();

    public static void main(String[] args) {
        System.out.println("开始测试写锁降级");
        new Thread(() -> writeDowngrade()).start();
        System.out.println("-------------------");
        System.out.println("开始测试读锁升级");
        new Thread(() -> readUpgrading()).start();




//TODO      在非公平锁的情况下：第一步写锁获取到锁，写锁是排它的。所以其他的线程只能等待。
//TODO      第二步：当写锁释放的一瞬间，这个时候子线程刚好在获取读锁，读锁是非互斥锁。刚好在任务队列中前面的也是读锁线程。所以这个时候就发生了读锁插队。
    }


    public static void readUpgrading(){
        System.out.println(Thread.currentThread().getName()+"：读锁开始尝试获取锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"：读锁获取了锁");
            System.out.println(Thread.currentThread().getName()+"：升级锁，会发生阻塞!");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"：升级锁成功！");
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"：读锁释放了锁");
            readLock.unlock();
        }
    }

    public static void writeDowngrade(){
        System.out.println(Thread.currentThread().getName()+"：写锁开始尝试获取锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"：写锁获取了锁");
            System.out.println(Thread.currentThread().getName()+"：开始降级锁!");
            readLock.lock();
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"：释放降级锁");
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"：写锁释放了锁");
            writeLock.unlock();
        }
    }

}