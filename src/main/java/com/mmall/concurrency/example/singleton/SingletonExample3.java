package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    /**
     * Static 关键字
     *
     * 如果getSingleton()方法想要被外部类调用只能加上static关键字
     *
     * static修饰的方法只能使用static关键字所修饰的常量
     */
    private static SingletonExample3 singletonExample1 = null;


    private SingletonExample3() {

    }

    public static synchronized SingletonExample3 getSingleton(){
        if (singletonExample1==null) {
            singletonExample1 = new SingletonExample3();
        }
        return singletonExample1;
    }


}
