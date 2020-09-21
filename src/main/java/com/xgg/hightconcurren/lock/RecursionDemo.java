package com.xgg.hightconcurren.lock;

import java.security.acl.LastOwnerException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/14 14:56
 * @description TODO
 **/
public class RecursionDemo {

    static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) {
        accessResource();
    }

    public static void accessResource(){
        lock.lock();
        try {
            System.out.println("处理了资源");
            if(lock.getHoldCount()<5){
                System.out.println(lock.getHoldCount());
                accessResource();
                System.out.println(lock.getHoldCount());
            }
        }finally {
            lock.unlock();
        }
    }
}
