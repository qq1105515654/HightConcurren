package com.xgg.hightconcurren.threadpool;

import jdk.internal.org.objectweb.asm.tree.FieldInsnNode;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/7 10:34
 * @description TODO  用于演示钩子方法
 **/
public class PauseableThreadPool extends ThreadPoolExecutor {

    /**
     * 是否暂停
     **/
    private boolean isPause;

    private final ReentrantLock lock=new ReentrantLock();

    private Condition unPaused=lock.newCondition();

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
        try {
            //在线程执行之前，判断我们是否想让这个线程暂停
            while(isPause){
                //Condition是跟锁绑定的
                unPaused.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * @author snh
     * @date 10:45 2020/9/7
     * @param
     * @return void
     * @Description TODO 暂停当前线程的执行
     **/
    public void pause(){
        lock.lock();
        try {
            isPause=true;
        }finally {
            lock.unlock();
        }
    }

    /**
     * @author snh
     * @date 10:45 2020/9/7
     * @param
     * @return void
     * @Description TODO 唤醒线程
     **/
    public void resume(){
        lock.lock();
        try {
            isPause=false;
            unPaused.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        PauseableThreadPool pauseableThreadPool=new PauseableThreadPool(10,20,10L,TimeUnit.SECONDS,new LinkedBlockingQueue<>());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "：我被执行了。");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 10000; i++) {
            pauseableThreadPool.execute(runnable);
        }
        Thread.sleep(1500);
        pauseableThreadPool.pause();
        System.out.println("线程池暂停了");
        Thread.sleep(1500);
        pauseableThreadPool.resume();
        System.out.println("线程池恢复了");

    }

}
