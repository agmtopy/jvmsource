package com.agmtopy.source.executer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorServiceUtil2 {
    private static final int CORE_SIZE = 8;
    private static final int TASK_SIZE = 10000;

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_SIZE, CORE_SIZE, 0,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(TASK_SIZE + 2048), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 8; i <= 8192; i = i * 2) {
            CountDownLatch latch = new CountDownLatch(TASK_SIZE);
            long st = System.currentTimeMillis();
            execute(i, latch);
            latch.await();
            long et = System.currentTimeMillis();
            System.out.printf("线程数量为[%s]执行耗时[%s]ms %n", i, et - st);
            System.out.printf("线程池信息为[%s] %n", THREAD_POOL_EXECUTOR);
        }
        THREAD_POOL_EXECUTOR.shutdown();
    }

    private static void execute(int coreSize, CountDownLatch latch) throws InterruptedException {
        THREAD_POOL_EXECUTOR.setMaximumPoolSize(coreSize);
        THREAD_POOL_EXECUTOR.setCorePoolSize(coreSize);
        IntStream.range(0, TASK_SIZE).forEach(i -> THREAD_POOL_EXECUTOR.submit(new ExecutorServiceUtil.Task(latch, i)));
    }

}
