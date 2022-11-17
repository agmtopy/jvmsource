package com.agmtopy.source.thread

import java.util.*
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask

fun main() {
    val poolExecutor = ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, LinkedBlockingDeque())
    val future = poolExecutor.submit { println("打印当前时间:" + 1 / 0) }

    val timer = Timer()
    timer.schedule(timerTask { println("开始打印:" + poolExecutor.toString()) }, 1000, 1000)

    println(future.get())

}