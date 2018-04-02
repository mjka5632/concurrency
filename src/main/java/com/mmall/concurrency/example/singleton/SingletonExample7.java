package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.Recommend;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式 ：最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7() {

    }

    public static SingletonExample7 getsignle() {

        return Singleton.INSTANCE.getsignle();

    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 example7;

        /**
         * JVM保证这个方法只会执行一次
         */
        Singleton() {
            example7 = new SingletonExample7();
        }

        public SingletonExample7 getsignle() {
            return example7;
        }
    }

}
