package com.zy.myeyes.test.commons;

import com.zy.myeyes.beans.Person;
import com.zy.myeyes.beans.User;
import com.zy.myeyes.service.PersonService;
import com.zy.myeyes.service.UserService;
import net.sf.ehcache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by zhougb on 2016/2/25.
 */
public class TestSpringAop {
    public static void main(String args[]){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testspring.xml");
        PersonService personService = (PersonService) applicationContext.getBean("personServiceImpl");
        Person person = new Person();
        person.setName("adfas");
        personService.save(person);

        Person person1 = personService.findById(person.getId());
        System.out.println("get person1:"+person1);
        person1 = personService.findById(person.getId());
        System.out.println("get person2:"+person1);
        person1 = personService.findById(person.getId());
        System.out.println("get person3:"+person1);
        person1 = personService.findById(person.getId());
        System.out.println("get person4:"+person1);
        ///Sleepable sleepable = (Sleepable) applicationContext.getBean("sleepProxy");
        //sleepable.sleep();

        /*Human human = (Human) applicationContext.getBean("human");
        human.sleep();*/

        /*Sleepable sleepable = (Sleepable) applicationContext.getBean("human");
        sleepable.sleep();
        sleepable.hulu();*/

        /*UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        System.out.println("get UserService "+userService);
        User user = userService.register("zy", "123456");
        System.out.println("do userService result:"+user+" "+user.getUid());*/

        /*user = userService.queryById(4);
        System.out.println("queryById user:"+user);

        user.setUserName("afasdf");
        user.setUid(4);
        int result = userService.update(user);
        System.out.println("update result:"+result);

        //result = userService.deleteById(4);
        //System.out.println("delete result:"+result);

        List<User> list = userService.findAll();
        System.out.println(list.size());*/

        //CacheManager.

        //applicationContext.

        System.exit(0);
    }
}
