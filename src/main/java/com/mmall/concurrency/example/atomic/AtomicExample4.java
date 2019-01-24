package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class AtomicExample4 {


    public static AtomicReference count=new AtomicReference(0);

    public static void main(String[] args) throws Exception {
       count.compareAndSet(0,1);
       count.compareAndSet(1,3);
       count.compareAndSet(2,4);
       log.info("count:{}",count);
    }



}
