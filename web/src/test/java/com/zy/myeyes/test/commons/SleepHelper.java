package com.zy.myeyes.test.commons;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by zhougb on 2016/2/25.
 */
public class SleepHelper implements MethodBeforeAdvice, AfterReturningAdvice{

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("通常情况下睡觉之前要脱衣服！");
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("起床后要先穿衣服！");
    }
}
