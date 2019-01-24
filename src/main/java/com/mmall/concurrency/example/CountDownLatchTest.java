package com.mmall.concurrency.example;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: mrt
 * @Description:
 * @Date: Created in 10:08 2019/1/22
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        /**
         * 计数器
         */
        CountDownLatch latch = new CountDownLatch(2);
        /**
         * 普通方式
         */
/*       //jdk1.8以前
 new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    //值减一
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();*/

        /**
         * jdk1.8
         * lambda表达式创建线程
         */
        new Thread(() -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                //值减一
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                //值减一
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            System.out.println("等待2个子线程执行完毕...");
            //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
