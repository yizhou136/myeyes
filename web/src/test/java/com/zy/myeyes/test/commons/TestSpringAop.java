package com.zy.myeyes.test.commons;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhougb on 2016/2/25.
 */
public class TestSpringAop {
    public static void main(String args[]){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testspring.xml");
        ///Sleepable sleepable = (Sleepable) applicationContext.getBean("sleepProxy");
        //sleepable.sleep();

        /*Human human = (Human) applicationContext.getBean("human");
        human.sleep();*/

        Sleepable sleepable = (Sleepable) applicationContext.getBean("human");
        sleepable.sleep();
        sleepable.hulu();
        //System.out.println();
    }
}
