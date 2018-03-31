package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 *
 * 优点：线程安全
 *
 * 缺点：占内存，影响性能
 *
 * 类装载时创建对象[singletonExample1 = getSingleton()]
 */
@ThreadSafe
public class SingletonExample2 {
    /**
     * Static 关键字
     *
     * 如果getSingleton()方法想要被外部类调用只能加上static关键字
     *
     * static修饰的方法只能使用static关键字所修饰的常量
     */
    private static SingletonExample2 singletonExample1 = getSingleton();


    private SingletonExample2() {

    }

    public static SingletonExample2 getSingleton(){

        return new SingletonExample2();
    }
}
