package com.mmall.concurrency.example.count;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class CountExample3 {
    /**
     * 请求总数
     */
    public static int clientTotal = 5000;
    /**
     * 允许最大并发量
     */
    public static int threadTotal = 200;
    /**
     * volatile关键字刷新到住内存中
     */
    public static volatile int count = 0;

    public static void main(String[] args) throws Exception {


        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }

                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        log.info("count:{}", count);

    }

    public static void add() {
        count++;
    }

}