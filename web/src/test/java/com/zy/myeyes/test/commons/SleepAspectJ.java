package com.zy.myeyes.test.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.stereotype.Component;

/**
 * Created by zhougb on 2016/2/26.
 */
@Aspect
@Component
public class SleepAspectJ {
    private static final Log logger = LogFactory.getLog(SleepAspectJ.class);

    @Pointcut("execution(* com.zy.myeyes.test.commons..*.sleep(..)))")
    public void sleepPoint(){}

    @Before("sleepPoint()")
    public void beforeSleep(){
        System.out.println("通常情况下睡觉之前要脱衣服！");
    }

    @AfterReturning("sleepPoint()")
    public void afterSleep(){
        System.out.println("起床后要先穿衣服！");
    }

    @Around("execution(* *.hulu())")
    public void afterHulu(JoinPoint joinPoint){
        logger.debug("begin hulu");
        System.out.println("begin hulu");
        try {
            ((ProceedingJoinPoint)joinPoint).proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after hulu");
        logger.debug("after hulu");
    }

    public static void main(String args[]){
        Human human1 = new Human(1);
        Human human2 = new Human(2);
        /*AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.zy.myeyes.test.commons..*.sleep(..)))");
        SleepHelper  sleepAdvice = new SleepHelper();
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(pointcut, sleepAdvice);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(human);
        proxyFactory.addAdvisor(defaultPointcutAdvisor);
        Sleepable sleepable = (Sleepable) proxyFactory.getProxy();
        sleepable.sleep();*/



        //SingletonTargetSource singletonTargetSource = new SingletonTargetSource(human1);
        HotSwappableTargetSource targetSource = new HotSwappableTargetSource(human1);
        Human proxy = (Human) ProxyFactory.getProxy(targetSource);
        System.out.println(proxy.getClass().getName());
        proxy.sleep();

        targetSource.swap(human2);
        //human1.setAge(3);
        proxy.sleep();
        //singletonTargetSource.releaseTarget();

    }
}
