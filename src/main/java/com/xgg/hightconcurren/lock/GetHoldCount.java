package com.xgg.hightconcurren.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/14 14:48
 * @description TODO 可重入锁演示
 **/
public class GetHoldCount {

    static ReentrantLock lock=new ReentrantLock();


    public static void main(String[] args) {
        //TODO 可重入锁的特征是无需解锁，可重复获取锁
        //TODO 不可重入锁的特征是，我们获取锁之后如果不解锁就无法再次获取同一个锁，否则会造成死锁。
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }
}
