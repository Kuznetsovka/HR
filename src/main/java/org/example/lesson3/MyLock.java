package org.example.lesson3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyLock implements Runnable {
    private int count = 0;
    private int maxCount = 10;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();

    public MyLock(int count,int maxCount) {
        this.count = count;
        this.maxCount = maxCount;
    }

    public MyLock() {
    }

    public void incrementAndPrint() {
        while (count<maxCount) {
            writeLock.lock();
            try {
                System.out.println(++count);
            } finally {
                writeLock.unlock();
            }
        }
    }

    @Override
        public void run() {
            incrementAndPrint();
        }

    public static void main(String[] args) {
        int countThread = 4;
        ExecutorService service = Executors.newFixedThreadPool(countThread);
        MyLock mylock = new MyLock();
        for (int i = 0; i < countThread; i++) {
            service.execute(mylock);
        }
        service.shutdown();
    }

}
