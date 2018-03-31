package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式
 *
 * 类第一次使用时就创建对象[singletonExample1 = null]
 */
@NotThreadSafe
public class SingletonExample1 {
    /**
     * Static 关键字
     *
     * 如果getSingleton()方法想要被外部类调用只能加上static关键字
     *
     * static修饰的方法只能使用static关键字所修饰的常量
     */
    private static SingletonExample1 singletonExample1 = null;


    private  SingletonExample1() {

    }

    public static SingletonExample1 getSingleton(){
        if (singletonExample1==null) {
            singletonExample1 = new SingletonExample1();
        }
        return singletonExample1;
    }
}
