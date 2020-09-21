package com.xgg.hightconcurren.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/9 17:44
 * @description TODO Lock 不会像 synchronized 发生异常的时候自动释放锁，所以我们需要在 finally 中主动去释放锁
 **/
public class MustUnlock {

    private static Lock lock=new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println("李四");
        }finally {
            lock.unlock();
        }
    }
}
