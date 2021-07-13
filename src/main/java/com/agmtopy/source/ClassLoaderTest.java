package com.agmtopy.source;

import java.lang.reflect.InvocationTargetException;

public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println("-----------------loadClass-----------");
        loadClass();
        System.out.println("-----------------forClassName-----------");
        forClassName();
    }

    public static void loadClass() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            Class<?> loadClass = classLoader.loadClass("com.agmtopy.source.StaticClass");
            var instance = loadClass.getDeclaredConstructor().newInstance();
            System.out.println("loadClass:" + instance.getClass().getSimpleName());
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void forClassName() {
        try {
            Class.forName("com.agmtopy.source.StaticClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
