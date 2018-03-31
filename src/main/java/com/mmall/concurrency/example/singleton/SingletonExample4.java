package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 *
 * Static 关键字:
 * 如果getSingleton()方法想要被外部类调用只能加上static关键字
 * static修饰的方法只能使用static关键字所修饰的常量
 *
 * volatile关键字：
 * 修饰的共享变量在读取到变量时是最新的
 * 防止指令重排
 *
 */
@ThreadSafe
public class SingletonExample4 {


    //单例对象 ：volatile + 双重检测机制 -》禁止指令重排
    private static volatile SingletonExample4 singletonExample1 = null;


    private SingletonExample4() {

    }

    public static  SingletonExample4 getSingleton(){
        if (singletonExample1==null) {
            //双重检测机制
            synchronized (SingletonExample4.class){//添加同步锁
                if(singletonExample1==null){
                    singletonExample1 = new SingletonExample4();
                }
            }

        }
        return singletonExample1;
    }


}
