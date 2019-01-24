package com.mmall.concurrency.example;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CyclicBarrier(超时)
 * 通过它可以实现让一组线程等待至某个状态之后再全部同时执行
 *
 * @Author: mrt
 * @Description:
 * @Date: Created in 11:10 2019/1/22
 */
public class CyclicBarrierTest4 {
    public static void main(String[] args) {
        int N = 4;
        //jdk1.8
      /*  CyclicBarrier barrier = new CyclicBarrier(N,
                () -> {System.out.println("当前线程" + Thread.currentThread().getName());});*/
        //或者
        CyclicBarrier barrier = new CyclicBarrier(N,
                () -> System.out.println("当前线程" + Thread.currentThread().getName()));
      /*  //jdk1.8以前
        CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程" + Thread.currentThread().getName());
            }
        });*/
        for (int i = 0; i < N; i++) {
            if (i < N - 1) {
                new Writer(barrier).start();
            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                new Writer(barrier).start();
            }
        }

    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                //以睡眠来模拟写入数据操作
                Thread.sleep(5000);
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "所有线程写入完毕，继续处理其他任务...");
        }
    }
}

