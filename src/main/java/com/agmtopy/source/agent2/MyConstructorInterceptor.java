package com.agmtopy.source.agent2;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Executable;

public class MyConstructorInterceptor {

    @Advice.OnMethodEnter
    public static boolean before(@Advice.Origin Executable method) {
        System.out.println("[C] >> " + method);
        return true;
    }

    @Advice.OnMethodExit
    public static void after(@Advice.Origin Executable method) {
        System.out.println("[C] << " + method);
    }

}
