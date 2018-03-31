package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 *
 * 优点：线程安全
 *
 *
 * 类装载时创建对象[singletonExample1 = getSingleton()]
 */
@ThreadSafe
public class SingletonExample5 {
    /**
     * Static 关键字
     *
     * 如果getSingleton()方法想要被外部类调用只能加上static关键字
     *
     * static修饰的方法只能使用static关键字所修饰的常量
     */
    private static SingletonExample5 singletonExample1 = null;

    static{
         singletonExample1 = new SingletonExample5();
    }


    private SingletonExample5() {

    }

    public static SingletonExample5 getSingleton(){

        return singletonExample1;
    }

    public static void main(String[] args) {
        System.out.println(getSingleton().hashCode());
        System.out.println(getSingleton().hashCode());
    }
}
