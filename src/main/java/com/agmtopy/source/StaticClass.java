package com.agmtopy.source;

public class StaticClass {
    private static Integer i = 10;

    static{
        System.out.println(Thread.currentThread().getName() + "开始打印...");
    }

}
