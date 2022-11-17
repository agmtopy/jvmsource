package com.agmtopy.source.thread

class TaskDemoKt: Thread() {
    override fun run() {
        super.run()
        println( currentThread().name + "执行任务")
    }
}

fun main(){
    val demoKt = TaskDemoKt()
    demoKt.start()
}