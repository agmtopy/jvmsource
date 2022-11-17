package com.agmtopy.source.thread

import java.time.LocalDateTime
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

fun main() {
    //以创建同步队列的线程池为例
    val poolExecutor = ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, LinkedBlockingDeque())
    IntRange(0, 10).forEach { _ -> poolExecutor.submit { println("打印当前时间:" + LocalDateTime.now()) } }
}