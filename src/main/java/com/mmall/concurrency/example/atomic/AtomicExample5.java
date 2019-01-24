package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {
    private static AtomicIntegerFieldUpdater<AtomicExample5> count = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "N");

    @Getter
    volatile int N = 5;

    public static void main(String[] args) throws Exception {
        AtomicExample5 atomicExample5 = new AtomicExample5();
        if (count.compareAndSet(atomicExample5, 5, 10)) {
            log.info("success" + atomicExample5.getN());
        }

        if (count.compareAndSet(atomicExample5, 5, 10)) {
            log.info("success" + atomicExample5.getN());
        } else {
            log.info("fail" + atomicExample5.getN());
        }
    }


}
