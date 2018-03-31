package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
/**
 * 对象逸出
 *
 * 可能InnerClass对象
 * 还没创建完成就被启动啦
 */
public class Escape {

    private int i=0;


    public Escape() {
        
        new InnerClass();

    }

    public class InnerClass{

        public InnerClass() {
            log.info("{}",Escape.this.i);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
