package com.agmtopy.source.internal;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;




public class InternalA {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

    }
}
