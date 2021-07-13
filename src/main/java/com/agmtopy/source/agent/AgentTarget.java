package com.agmtopy.source.agent;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class AgentTarget {
    public static void main(String[] args) throws Exception {
        System.out.println("开始打印....");
        AgentTarget agentTarget = new AgentTarget();
        TimeUnit.SECONDS.sleep(10);

        agentTarget.printInfo();
        while (true) {
            TimeUnit.SECONDS.sleep(5);
            agentTarget.println();
        }
    }

    public void println() {
        System.out.println(LocalTime.now());
    }

    public void printInfo() {
        System.out.println("123" + LocalTime.now());
    }
}
