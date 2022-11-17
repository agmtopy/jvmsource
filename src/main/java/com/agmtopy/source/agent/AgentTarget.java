package com.agmtopy.source.agent;

import java.time.LocalTime;

public class AgentTarget {
    public static void main(String[] args) throws Exception {
        System.out.println("开始打印....");
        AgentTarget agentTarget = new AgentTarget();
        agentTarget.printInfo();
    }

    public void printInfo() {
        System.out.println("123" + LocalTime.now());
    }
}
