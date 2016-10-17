package com.zy.myeyes.test.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by zhougb on 2016/7/21.
 */
public class BeanLifeCycle {
    private final static Logger logger = LoggerFactory.getLogger(BeanLifeCycle.class);


    public static void main(String[] args) {
        logger.debug("现在开始初始化容器");
        ApplicationContext factory = new ClassPathXmlApplicationContext("bean.xml");
        logger.debug("容器初始化成功");
        //得到Preson，并使用
        /*Person person = (Person) factory.getBean("person");
        logger.debug("person:"+person.toString());
        person.sleep();
        person.hashCode();*/

        ServiceA serviceA = (ServiceA) factory.getBean("serviceA");
        serviceA.readPerson();

        logger.debug("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }
}
