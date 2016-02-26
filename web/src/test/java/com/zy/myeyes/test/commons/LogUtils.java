package com.zy.myeyes.test.commons;

import org.aspectj.lang.annotation.Aspect;

/**
 * Created by zhougb on 2016/2/26.
 */
@Aspect
public class LogUtils {

    public void logA(){
        System.out.println("print A");
    }


    public void logB(){
        System.out.println("print B");
    }
}
