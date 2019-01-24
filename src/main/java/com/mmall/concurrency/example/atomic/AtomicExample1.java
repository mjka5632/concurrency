package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger
 */
@Slf4j
@ThreadSafe
public class AtomicExample1 {
    /**
     * 请求总数
     */
    public static int clientTotal = 5000;
    /**
     * 允许最大并发量
     */
    public static int threadTotal = 200;
    /**
     *
     */
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器
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
        log.info("count:{}", count.get());

    }

    public static void add() {
//        count.incrementAndGet();
        count.getAndIncrement();
    }

}
