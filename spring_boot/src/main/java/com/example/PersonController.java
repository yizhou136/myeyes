package com.example;

import com.example.beans.Person;
import com.example.dao.PersonRedisDao;
import com.example.dao.PersonRepository;
import com.example.service.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhougb on 2016/8/17.
 */
@RestController
public class PersonController {
    private static final Log logger = LogFactory.getLog(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired(required = true)
    private PersonRepository personRepository;

    @Autowired
    private PersonRedisDao personRedisDao;

    @RequestMapping("/pc/save")
    public Person save(String name, int age, String address){
        Person person = personRepository.save(new Person(null, name, age, address));
        return person;
    }

    @RequestMapping(value = "/pc/q1", consumes = "application/json")
    public List<Person> q1(String address){
        return personRepository.findByAddress(address);
    }


    @RequestMapping("/pc/q2")
    public Person q2(String name, String address){
        return personRepository.findByNameAndAddress(name, address);
    }

    @RequestMapping("/pc/q3")
    public Person q3(String name, String address){
        return personRepository.withNameAndAddressQuery(name, address);
    }

    @RequestMapping("/pc/q4")
    public Person q4(String name, String address){
        return personRepository.withNameAndAddressNamedQuery(name, address);
    }

    @RequestMapping("/pc/sort")
    public List<Person> sort(){
        return personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
    }


    @RequestMapping("/pc/page")
    public Page<Person> page(){
        return personRepository.findAll(new PageRequest(1, 2));
    }


    @RequestMapping("/v/auto")
    public Page<Person> auto(Person person){
        return null;//personRepository.findByAuto(person, new PageRequest(1, 10));
    }

    /*public Person  get(){
        return personService.
    }*/


    @RequestMapping("/pc/update")
    public int update(Person person){
        return personRepository.updatePerson(person.getAddress(), person.getPid());
    }

    @RequestMapping("/pc/{pid}")
    public Person findByPid(@PathVariable("pid") Long pid){
        logger.info("findByPid pid:"+pid);
        Person person =  personRepository.findByPid(pid);


        /*personRedisDao.save(person);
        logger.info("get Person from redis: pid:"+pid);
        person = personRedisDao.getPerson(person.getPid());*/

        return person;
    }
}