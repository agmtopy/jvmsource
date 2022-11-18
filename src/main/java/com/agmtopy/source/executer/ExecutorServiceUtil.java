package com.agmtopy.source.executer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorServiceUtil {

    private static final int taskNum = 1000000;

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(taskNum + 2048));

    public static void main(String[] args) throws InterruptedException {
        for (int i = 8; i <= 16384; i = i * 2) {
            CountDownLatch latch = new CountDownLatch(taskNum);
            System.out.printf("线程数量为[%s]正在执行... %n", i);
            threadPoolExecutor.setMaximumPoolSize(i);
            threadPoolExecutor.setCorePoolSize(i);
            long st = System.currentTimeMillis();
            extracted(latch);
            latch.await();
            long et = System.currentTimeMillis();
            System.out.printf("线程数量为[%s]执行耗时[%s]ms %n", i, et - st);
        }
        threadPoolExecutor.shutdown();
    }

    private static void extracted(CountDownLatch latch) {
        IntStream.range(0, taskNum).forEach(i -> threadPoolExecutor.submit(new Task(latch, i)));
    }

    static class Task implements Runnable {
        private int taskId;
        private CountDownLatch latch;

        Task(CountDownLatch latch, int taskId) {
            this.latch = latch;
            this.taskId = taskId;
        }


        @Override
        public void run() {
            doExecute();
            try {
                TimeUnit.MICROSECONDS.sleep(1);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void doExecute() {
        int min = 0;
        int max = 1000;
        for (int i = min; i <= max; i++) {
            isPrime2(i);
        }
    }


    public static boolean isPrime2(int n) {
        if (n <= 3) {
            return n > 1;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


}


