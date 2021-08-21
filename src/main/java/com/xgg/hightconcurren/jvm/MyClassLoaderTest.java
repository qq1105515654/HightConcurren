package com.xgg.hightconcurren.jvm;

import sun.misc.Resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

public class MyClassLoaderTest {


    static class MyClassLoader extends ClassLoader{

        private String classPath;

        public MyClassLoader(String classPath){
            this.classPath=classPath;
        }

        public byte[] loadByte(String name) throws IOException {
            name=name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name+".class");
            int len=fis.available();
            byte[] data=new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        /**
         * 打破双亲委派机制的关键方法
         * @param name
         * @param resolve
         * @return
         * @throws ClassNotFoundException
         */
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
        {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
                    if(!name.startsWith("com.xgg.hightconcurren")){
                        c = this.getParent().loadClass(name);
                    }else{
                        c = findClass(name);
                    }
                    long t1 = System.nanoTime();
                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }

        //实现自定义类加载器的关键方法
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {

            try {
                byte[] data=loadByte(name);
                return defineClass(name,data,0,data.length);
            } catch (IOException e) {
                e.printStackTrace();
                throw  new ClassNotFoundException();
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //使用自定义类加载器去加载指定路径下的类
        MyClassLoader classLoader=new MyClassLoader("D:/classLoaderRoot/test");
        Class clazz = classLoader.loadClass("com.xgg.hightconcurren.jvm.User");
        Object obj=clazz.newInstance();
        Method method=clazz.getDeclaredMethod("print", null);
        method.setAccessible(true);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());


        MyClassLoader classLoader1=new MyClassLoader("D:/classLoaderRoot/test1");
        Class clazz1 = classLoader1.loadClass("com.xgg.hightconcurren.jvm.User");
        Object obj1=clazz1.newInstance();
        Method method1=clazz1.getDeclaredMethod("print", null);
        method1.setAccessible(true);
        method1.invoke(obj1, null);
        System.out.println(clazz1.getClassLoader().getClass().getName());


    }
}
