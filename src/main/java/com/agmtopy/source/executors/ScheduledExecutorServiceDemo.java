package com.agmtopy.source.executors;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    //测试单线程的线程池
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName() + " - 打印1:" + LocalTime.now()), 10, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName() + " - 打印2:" + LocalTime.now()), 10, 5, TimeUnit.SECONDS);
    }
}
