package com.xgg.hightconcurren.list;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ArrayListTestFastFail {

    public static void main(String[] args) {
        /*Integer[] i=new Integer[]{1,2,3,4,5,6,7};

        List<Integer> arr = Arrays.stream(i).collect(Collectors.toList());
        for (Integer e : arr) {
            System.out.println(e);
            arr.add(50);
        }*/

        int i =9;
        System.out.println(i+(i >> 1));
        // i >> 1 = i/2
        // i >> 2 = i / (2²)

        // 9  的 二进制数 1 0 0 1
        //10  的 二进制数  通过计算= 0 1 0 1 然后将结果翻转= 1010

        ArrayList<Object> arrList = new ArrayList<>();

        for (int j = 0; j < 15; j++) {
            arrList.add(j);
        }

       /*Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(time));*/
    }


}
