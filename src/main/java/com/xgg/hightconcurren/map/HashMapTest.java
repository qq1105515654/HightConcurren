package com.xgg.hightconcurren.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HashMapTest {


    public static void main(String[] args) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("sz","张三");
        int i;
        int n=16;
        int hash=2356;
        // 2356 / 2 = 1178   余 0
        // 1178 / 2 = 589    余 0
        // 589  / 2 = 294    余 1
        // 294  / 2 = 147    余 0
        // 147  / 2 = 73     余 1
        // 73   / 2 = 36     余 1
        // 36   / 2 = 18     余 0
        // 18   / 2 = 9      余 0
        // 9    / 2 = 4      余 1
        // 4    / 2 = 2      余 0
        // 2    / 2 = 1      余 0
        // 1    / 2 = 0      余 1
        // 将计算结果反转得出 2356 二进制= 100100110100
        int a=2*2; // 从右到左开始计算 2 的 次方 ，第一位 = 2的 0次方，第二位=2的1次方 以此类推。
        int b=2*2*2*2;
        int c=2*2*2*2*2;
        int d=2*2*2*2*2*2*2*2;
        int e=2*2*2*2*2*2*2*2*2*2*2;
        System.out.println(a+b+c+d+e);
        // 16   / 2 = 8      余 0
        // 8    / 2 = 4      余 0
        // 4    / 2 = 2      余 0
        // 2    / 2 = 1      余 0
        // 1    / 2 = 0      余 1
        //将计算结果反转得出 16 二进制 = 10000
        //由于这里n-1=15
        //15    / 2 = 7      余 1
        //7     / 2 = 3      余 1
        //3     / 2 = 1      余 1
        //1     / 2 = 0      余 1
        //将计算结果反转得出 15 二进制 = 1111
        // & 表示如果相对应的位都为1 则结果为1 否则 为0
        //100100110100
        //000000001111
        //0100
        //0100 计算结果 = 2的 2次方 = 4
        //所以 (n-1) & hash = 4
        i=(n-1) & hash;
        // ^ 表示如果对应的位值相同，则结果为 0 ，否则为 1
        //100100110100
        //000000001111
        //100100111011
        //100100111011 计算结果为 2363
        int f=(n-1) ^ hash;
        System.out.println("& 结果："+i);
        System.out.println("^ 结果："+f);

        if(a>2){
            System.out.println("if 被执行了");
        }else if(b >2){
            System.out.println("else if 被执行了");
        }

        int l=16;
        // 16   / 2 = 8      余 0
        // 8    / 2 = 4      余 0
        // 4    / 2 = 2      余 0
        // 2    / 2 = 1      余 0
        // 1    / 2 = 0      余 1
        // 16 的二进制 = 10000
        // 16 >>> 2 = 00100 = 4
        System.out.println(l>>>2);

        List<String> list=new ArrayList<>();
        list.add("zs");
        list.add("ww");


        List<String> zs = list.stream().map(item -> {
            if("zs".equals(item)){
                item="ls";
            }
            return item;
        }).collect(Collectors.toList());
        System.out.println(zs);
        
    }
}
