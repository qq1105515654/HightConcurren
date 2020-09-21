package com.xgg.hightconcurren.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/14 17:38
 * @description TODO 演示读写锁
 **/
public class CinemaReadWrite {
    private static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {
        new Thread(() -> write()).start();
        new Thread(() -> read()).start();
        new Thread(() -> read()).start();
        new Thread(() -> write()).start();
        new Thread(() -> read()).start();
    }

    public static void read(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"拿到读锁，正在读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放读锁");
            readLock.unlock();
        }
    }

    public static void write(){
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"拿到写锁，正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
        }
    }

}
