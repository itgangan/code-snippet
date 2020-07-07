package com.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        // 测试countDownLatch
//        countDownLatch();

        // 测试join
//        join();

        // 尝试wait方法醒来
        waitAndNotify();

    }

    // 该方法实验了两件事
    // 第一，wait方法被notify之后，需要获取到锁才能接着执行
    // 第二，wait方法被nofity之后，执行的顺序是接着wait方法之后执行
    private static void waitAndNotify() {
        Object lock = new Object();
        Thread waitThread = waitThread(lock);
        Thread notifyThread = notifyThread(lock);
        waitThread.start();
        notifyThread.start();
    }

    private static Thread notifyThread(Object lock) {
        return new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);

                synchronized (lock) {
                    System.out.println("before notify");
                    lock.notify();
                    System.out.println("after notify");

                    TimeUnit.SECONDS.sleep(2);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
    }

    private static Thread waitThread(Object lock) {
        return new Thread(() -> {
            System.out.println("start thread.");
            synchronized (lock) {
                try {
                    System.out.println("before wait");
                    lock.wait();
                    System.out.println("after wait");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("end thread.");
        });
    }


    private static void join() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread1 = createTread(1);
        Thread thread2 = createTread(3);
        Thread thread3 = createTread(2);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        long end = System.currentTimeMillis();

        System.out.println("join:" + (end - start));
    }

    private static Thread createTread(int i) {
        return new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    private static void countDownLatch() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(3);

        long start = System.currentTimeMillis();
        threadRun(1, cdl);
        threadRun(3, cdl);
        threadRun(2, cdl);

        cdl.await();
        long end = System.currentTimeMillis();

        System.out.println("countDownLatch:" + (end - start));
    }

    private static void threadRun(int seconds, CountDownLatch cdl) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cdl.countDown();
        }).start();

    }
}
