package com.xgg.hightconcurren.list;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListTest {


    public static void main(String[] args) {
        LinkedList<String> linkedList=new LinkedList<>();

        linkedList.add(null);
        linkedList.add(null);

        System.out.println(linkedList.indexOf(null));
        System.out.println(linkedList.lastIndexOf(null));

        linkedList.push("123");
        linkedList.addLast("456");
        System.out.println(linkedList);

    }

    public static String getStackTrace(Throwable t){
        String stackTrace=null;
        if(t!=null){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            try {
                t.printStackTrace(pw);
                stackTrace = sw.toString();
            }finally {
                pw.close();
            }
        }
        return stackTrace;
    }

}
