package com.example;


import com.example.service.PersonService;
import com.example.service.TestJaxWsWebService;
import com.example.test.Person;
import com.example.test.service.AmqpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created by zhougb on 2016/8/11.
 */

@ComponentScan("com.example.test")
public class Test {

    @Value("${asfasdf}")
    private static final Log logger = LogFactory.getLog(User.class);

    public static void main2(String args[]){
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext("com.example.test");
        Person person = (Person) annotationConfigApplicationContext.getBean("person");
        logger.debug("person :"+person);

        PersonService personService = (PersonService) annotationConfigApplicationContext.getBean("rmiPersonService");
        logger.debug("personService :"+personService);
        com.example.beans.Person p = new com.example.beans.Person();
        p.setAge(32);
        p.setName("afas");
        com.example.beans.Person pp = personService.saveWithoutRollBack(p);
        logger.debug("personService invoked by rmi saveWithoutRollBack result:"+pp);

        /*personService =  (PersonService) annotationConfigApplicationContext.getBean("hessianPersonService");
        pp = personService.saveWithoutRollBack(p);
        logger.debug("personService invoked by hessian saveWithoutRollBack result:"+pp);*/

        /*TestJaxWsWebService testJaxWsWebService = (TestJaxWsWebService) annotationConfigApplicationContext.getBean("jaxwsCurrentDateService");
        Date date = testJaxWsWebService.currentDate();
        logger.info("date:"+date);*/

        AmqpService amqpService = (AmqpService) annotationConfigApplicationContext.getBean(AmqpService.class);
        logger.debug("amqpService :"+amqpService);
        amqpService.receivePerson();
    }

    public static void main(String args[]){
        Hashtable  hashtable = new Hashtable();
        for (int i=0; i< 10; i++) {
            String b = String.valueOf("b" + i);
            System.out.println(b.hashCode());
            hashtable.put(b, "a" + 1);
        }

        System.out.println(hashtable);
    }
}
