package org.example.lesson3;

public class MyTheads extends Thread{
    /*
    1. Реализовать программу, в которой два потока поочередно пишут ping и pong.
    2. Реализовать потокобезопасный счетчик с помощью интерфейса Lock.
     */
    public static void main(String[] args) throws InterruptedException {
        int count=0;
        while (count++<10) {
            Thread thread1 = new Thread(() -> System.out.println("ping"));
            thread1.start();
            thread1.join();
            Thread thread2 = new Thread(() -> System.out.println("pong"));
            thread2.start();
            thread2.join();
        }
    }

}
