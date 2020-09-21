package com.xgg.hightconcurren.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/14 15:43
 * @description TODO 演示公平和非公平锁的情况
 **/
public class FairLock {


    public static void main(String[] args) throws InterruptedException {
        QueuePrint queuePrint=new QueuePrint();
        Thread[] ts=new Thread[10];
        for (int i = 0; i < 10; i++) {
            ts[i]=new Thread(new Job(queuePrint));
        }
        for (Thread t : ts) {
            t.start();
            Thread.sleep(100);
        }
    }

}

class Job implements Runnable{
    private QueuePrint queuePrint;

    public Job(QueuePrint queuePrint) {
        this.queuePrint = queuePrint;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"准备打印");
        queuePrint.printJob(new Object());
        System.out.println(Thread.currentThread().getName()+"打印完毕");
    }
}

class QueuePrint{
    //是否为公平锁由 构造方法中传入的 true or  false 决定
    //TODO true  是否公平锁
//    private Lock lock=new ReentrantLock(true);
    //TODO false 非公平锁
    private Lock lock=new ReentrantLock(false);
    public void printJob(Object document){
        lock.lock();
        try{
            int duration= new Random().nextInt(10)+1;
            System.out.println(Thread.currentThread().getName()+"：正在打印，需要"+duration+"秒");
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        lock.lock();
        try{
            int duration= new Random().nextInt(10)+1;
            System.out.println(Thread.currentThread().getName()+"：正在打印，需要"+duration+"秒");
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
