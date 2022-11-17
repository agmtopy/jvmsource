package com.agmtopy.source.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Executable;

/**
 * 使用byte buddy处理
 */
public class AgentExampleForByteBuddy {

    @Advice.OnMethodEnter
    public static boolean before(@Advice.Origin Executable method) {
        System.out.println("byte buddy before : " + method);
        return true;
    }

    @Advice.OnMethodExit
    public static void after(@Advice.Origin Executable method) {
        System.out.println("byte buddy after : " + method);
    }

    public static void premain(String arguments, Instrumentation instrumentation) {
        System.out.println("开始执行...");
        new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .with(AgentBuilder.InstallationListener.StreamWriting.toSystemError())
                .type(ElementMatchers.nameContains("AgentTarget"))
                .transform((builder, td, cl, m) -> builder.visit(Advice.to(AgentExampleForByteBuddy.class).on(MethodDescription::isConstructor)))
                .installOn(instrumentation);
    }
}
