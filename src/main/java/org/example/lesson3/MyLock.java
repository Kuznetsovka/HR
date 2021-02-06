package org.example.lesson3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyLock implements Runnable {
    private final AtomicInteger count;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();

    public AtomicInteger getCount() {
        return count;
    }

    public MyLock(AtomicInteger count) {
        this.count = count;
    }

    public void incrementAndPrint() {
        writeLock.lock();
        readLock.lock();
        try {
            System.out.println(count.incrementAndGet());
        } finally {
            writeLock.unlock();
            readLock.unlock();
        }
    }

    @Override
        public void run() {
            incrementAndPrint();
        }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        AtomicInteger count = new AtomicInteger(0);
        MyLock mylock = new MyLock(count);
        while (mylock.getCount().get()<10) {
            service.execute(mylock);
        }
        service.shutdown();
    }

}
