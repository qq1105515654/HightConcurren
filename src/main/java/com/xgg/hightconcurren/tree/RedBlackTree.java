package com.xgg.hightconcurren.tree;

import java.net.SocketTimeoutException;
import java.util.HashMap;

public class RedBlackTree {

    public static void main(String[] args) {

        /*HashMap<String,Integer> map=new HashMap<>();

        for (int i = 0; i < 100; i++) {
            map.put(i+"", i);
        }

        long i= 1 << 4;
        long s=1 << 5;
        long k = 16 >> 4;
        long j = 1 << 30;

        System.out.println(i);
        System.out.println(k);
        System.out.println(s);
        System.out.println(j);*/

        int n= -5;
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);

    }

    public static class Node{

        //左子树
        private Node left;
        //右子树
        private Node right;
        //hash 值
        private int hashVal;
        //源对象
        private Object val;

        public Node getRight(){
            return this.right;
        }

        public Node getLeft(){
            return this.left;
        }

        public void setLeft(Node left){
            this.left=left;
        }

        public void setRight(Node right){
            this.right=right;
        }
    }
}
