package com.xgg.hightconcurren.jvm;

import org.openjdk.jol.info.ClassLayout;

public class LayoutClassSize {

    public static void main(String[] args) {
        ClassLayout layout=ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());

        System.out.println();
        ClassLayout layout1=ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());

        System.out.println();
        ClassLayout layout2 = ClassLayout.parseInstance(new User());
        System.out.println(layout2.toPrintable());

    }
}
