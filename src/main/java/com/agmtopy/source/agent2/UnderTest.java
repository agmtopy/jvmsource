package com.agmtopy.source.agent2;

public class UnderTest {
    static {
        System.out.println("static initialiser");
    }

    public UnderTest() {
        this("default");
        System.out.println("default constructor");
    }

    public UnderTest(String name) {
        System.out.println("constructor with parameter: " + name);
    }

    public int add(int a, int b) {
        System.out.println("instance method with parameters: " + a + ", " + b);
        return a + b;
    }

    static String greet(String recipient) {
        System.out.println("static method with parameter: " + recipient);
        return "Hello " + recipient;
    }
}
