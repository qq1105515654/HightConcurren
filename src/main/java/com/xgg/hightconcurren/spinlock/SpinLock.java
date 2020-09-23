package com.xgg.hightconcurren.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/22 16:14
 * @description TODO 演示自旋锁
 **/
public class SpinLock {

    private AtomicReference<Thread> sign=new AtomicReference<>();


    public static void main(String[] args) {

        SpinLock spinLock=new SpinLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "：开始尝试获取自旋锁");
                spinLock.lock();
                System.out.println(Thread.currentThread().getName() + "：获取自旋锁成功");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName() + "：释放自旋锁");
                }
            }
        };

        Thread t1=new Thread(runnable);
        Thread t2=new Thread(runnable);
        t1.start();
        t2.start();

    }

    public void lock(){
        Thread current=Thread.currentThread();
        while(!sign.compareAndSet(null,current)){
            System.out.println(current.getName()+"：获取自旋锁失败，再次尝试获取!");
        }
    }

    public void unlock(){
        Thread current=Thread.currentThread();
        sign.compareAndSet(current,null);
    }
}
