package com.agmtopy.source.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 自定义ClassFileTransformer
 */
public class ShowExecTime implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        //只针对目标包下进行耗时统计
        if (!className.startsWith("com/agmtopy/source/agent")) {
            return classfileBuffer;
        }
        System.out.println("正在加载类：" + className);

        try {
            ClassPool classPool = ClassPool.getDefault();
//            classPool.appendSystemPath();
            classPool.appendClassPath(new LoaderClassPath(loader));

            CtClass cl = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
            // 所有方法，统计耗时
            for (CtMethod method : cl.getDeclaredMethods()) {
                System.out.println("开始修改:" + method +" 方法" );
                //需要通过`addLocalVariable`来声明局部变量
                method.addLocalVariable("start", CtClass.longType);
                //插入 开始语句
                method.insertBefore("start = java.lang.System.currentTimeMillis();");
                String methodName = method.getLongName();
                //创建并插入 打印语句 System.out.println("方法：test， 执行时间：" + (System.currentTimeMillis() - start));
                String statement = String.format("java.lang.System.out.println(\"方法：%s， 执行时间：\" + (java.lang.System.currentTimeMillis() - start));", methodName);
                method.insertAfter(statement);
            }

            byte[] transformed = cl.toBytecode();
            return transformed;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}
