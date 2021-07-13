package com.agmtopy.source.agent;

import com.sun.tools.attach.VirtualMachine;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class AgentExampleDemo {

    /**
     * -javaagent:方式运行
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("premain start...");
        println(Thread.currentThread().getStackTrace());
    }

    /**
     * attach:方式运行
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain start...");
        // 显示执行时间
        inst.addTransformer(new ShowExecTime(), true);
        try {
            //重写载入新的字节码
            inst.retransformClasses(AgentTarget.class);
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印调用链
     */
    public static void println(StackTraceElement[] elements) {
        for (int i = 0; i < elements.length; i++) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("index: ").append(i).append(" ClassName: ").append(elements[i].getClassName())
                    .append(" Method Name : " + elements[i].getMethodName());
            System.out.println(buffer);
        }
    }

    /**
     * 修改指定运行中的代码
     */
    public static void main(String[] args) throws Exception {
        // 传入目标 JVM pid
        VirtualMachine vm = VirtualMachine.attach("21656");
        vm.loadAgent("D:\\project\\jvmsource\\build\\libs\\jvmsource-1.0-SNAPSHOT.jar");
    }
}