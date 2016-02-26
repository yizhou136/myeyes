package com.zy.myeyes.test.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
}
