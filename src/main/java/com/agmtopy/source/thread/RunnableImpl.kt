package com.agmtopy.source.thread

class RunnableImpl: Runnable{
    override fun run() {
        println("执行Runnable实现类")
    }
}

fun main(){
    //start是使用新的线程执行方法,run是当前线程执行的方法
    Thread(RunnableImpl()).start()
}