package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomicBoolean 只会执行一次
 */
@Slf4j
@ThreadSafe
public class AtomicExample6 {
    /**
     * 请求总数
     */
    public static int clientTotal = 5000;
    /**
     * 允许最大并发量
     */
    public static int threadTotal = 200;


    public static AtomicBoolean atomicBoolean = new AtomicBoolean(true);

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
//                    add();
                    flag();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }

                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

    }

    public static void flag() {
        if (atomicBoolean.compareAndSet(true, false)) {
            log.info("只会执行一次");
        }
    }

}
