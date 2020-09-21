package com.xgg.hightconcurren.lock;

import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/9 17:51
 * @description TODO
 **/
public class TryLockDeadlock implements Runnable {

    public int flag=1;

    static Lock lock1=new ReentrantLock();
    static Lock lock2=new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadlock t1=new TryLockDeadlock();
        TryLockDeadlock t2=new TryLockDeadlock();
        t1.flag=1;
        t2.flag=2;
        new Thread(t1).start();
        new Thread(t2).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(flag==1){
                try {
                    if(lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                        try {
                            System.out.println("线程1获取到了Lock1");
                            Thread.sleep(new Random().nextInt(1000));
                            if(lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println("线程1获取到了Lock2");
                                    System.out.println("线程1获取到了两把锁");
                                    break;
                                }finally {
                                    lock2.unlock();
                                    System.out.println("线程1释放了Lock2");
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            }else{
                                System.out.println("线程1获取Lock2失败，正在重试获取!");
                            }
                        } finally {
                            lock1.unlock();
                            System.out.println("线程1释放了Lock1");
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println("线程1获取Lock1失败，正在重试获取！");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(flag==2){
                try {
                    if(lock2.tryLock(800, TimeUnit.MILLISECONDS)){
                        try {
                            System.out.println("线程2获取到了Lock2");
                            Thread.sleep(new Random().nextInt(1000));
                            if(lock1.tryLock(800,TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println("线程2获取到了Lock1");
                                    System.out.println("线程2获取到了两把锁");
                                    break;
                                }finally {
                                    lock1.unlock();
                                    System.out.println("线程2释放了Lock1");
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            }else{
                                System.out.println("线程2获取Lock1失败，正在重试获取!");
                            }
                        } finally {
                            lock2.unlock();
                            System.out.println("线程2释放了Lock2");
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println("线程2获取Lock2失败，正在重试获取！");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
