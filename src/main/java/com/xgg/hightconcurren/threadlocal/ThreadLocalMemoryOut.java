package com.xgg.hightconcurren.threadlocal;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ThreadLocalMemoryOut {

    public static ThreadLocal<User> threadLocal=null;

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<User> threadLocal = getThreadLocal();
        User user = threadLocal.get();
        user.setAge("30");
        System.out.println("Main线程 "+user);

        MyThread myThread = new MyThread();
        myThread.run();
        System.out.println("Main线程 "+user);
    }


    public static ThreadLocal<User> getThreadLocal(){
        if(threadLocal==null){
            threadLocal=new ThreadLocal<>();
        }
        User user=new User();
        user.setAge("18");
        user.setName("张三");
        threadLocal.set(user);
        return threadLocal;
    }



    static class MyThread implements Runnable{

        @Override
        public void run() {

//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            String format = dateTimeFormatter.format(LocalDateTime.now());
//            System.out.println(format);
            ThreadLocal<User> threadLocal = getThreadLocal();
            User user = threadLocal.get();
            System.out.println("自定义线程 "+user);
        }
    }

    @Data
    static class User{

        private String name;

        private String age;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }
}
