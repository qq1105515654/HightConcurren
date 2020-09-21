package com.xgg.hightconcurren.threadlocal;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/9 10:18
 * @description TODO
 **/
public class ThreadLocalNormalUsage06 {

    public static void main(String[] args) {
        new Service1().process();
    }

}

class UserContextHolder{
    public static ThreadLocal<User> userHolder=new ThreadLocal<>();

}

class Service1{
    public void process(){
        User user=new User("张三");
        UserContextHolder.userHolder.set(user);
        new Service2().process();
    }
}

class Service2{
    public void process(){
        User user = UserContextHolder.userHolder.get();
        System.out.println(user);
        user.setName("李四");
        new Service3().process();
    }
}

class Service3{
    public void process(){
        User user = UserContextHolder.userHolder.get();
        System.out.println(user.getName());
        UserContextHolder.userHolder.remove();
    }
}


class User{

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
