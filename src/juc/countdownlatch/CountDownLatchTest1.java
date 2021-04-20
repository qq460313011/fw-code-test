package juc.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest1 {

    private static int LATCH_SIZE = 5;
    private static CountDownLatch downLatch;

    public static void main(String[] args) {
        downLatch = new CountDownLatch(LATCH_SIZE);


        for (int i = 0; i < LATCH_SIZE; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + " sleep 3000ms");
                    downLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("主线程开始等待");

        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程结束等待");
    }
}
