package com.agmtopy.source.agent2;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class ExampleAgent {
    public static void premain(String options, Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .with(AgentBuilder.InstallationListener.StreamWriting.toSystemError())
                .type(ElementMatchers.nameContains("UnderTest"))
                .transform((builder, td, cl, m) -> builder.visit(Advice.to(MyConstructorInterceptor.class).on(MethodDescription::isConstructor)))
                .installOn(instrumentation);
    }

    public static void main(String[] args) {
        premain(null, ByteBuddyAgent.install());
        UnderTest.greet("11");
        new UnderTest().add(1, 2);
    }
}