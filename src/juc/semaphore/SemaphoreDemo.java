package juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        //模拟三个就餐位
        Semaphore semaphore = new Semaphore(3);
        //模拟6个顾客
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //占位置
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到座位");
                    //吃饭等待3秒
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"吃完了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //离开就餐位
                    semaphore.release();
                }
            },String.valueOf(i)).start();


        }
    }
}
