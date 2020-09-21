package com.xgg.hightconcurren.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/10 10:52
 * @description TODO
 **/
public class LockInterruptibly implements Runnable {

    private Lock lock=new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        Thread thread1 = new Thread(lockInterruptibly);
        Thread thread2 = new Thread(lockInterruptibly);

        thread1.start();
        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"尝试获取锁");
        try {
            lock.lockInterruptibly();
            try{
                System.out.println(Thread.currentThread().getName()+"获取了锁");
                Thread.sleep(5000);
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+"在睡眠期间被打断了");
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放了锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"在获取锁期间被打断了");
        }
    }
}
