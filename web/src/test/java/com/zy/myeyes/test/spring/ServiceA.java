package com.zy.myeyes.test.spring;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhougb on 2016/7/23.
 */
public class ServiceA {
    private final static Log log = LogFactory.getLog(ServiceA.class);

    public Person readPerson(){
        log.debug("readPerson");
        return new Person();
    }

    public boolean insertPerson(Person person){
        log.debug("insertPerson"+ person);
        return true;
    }

    public void deletePerson(long pid){
        log.debug("deletePerson"+pid);
    }
}
