package com.example.service.impl;

import com.example.beans.Person;
import com.example.dao.PersonRepository;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhougb on 2016/8/17.
 */
@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(rollbackFor = IllegalArgumentException.class)
    public Person saveWithRollBack(Person person) {
        Person savedPerson = personRepository.save(person);

        if (savedPerson.getName().equals("abc1"))
            throw new IllegalArgumentException("数据已存在了!");

        return savedPerson;
    }

    @Override
    @Transactional(noRollbackFor = IllegalArgumentException.class)
    public Person saveWithoutRollBack(Person person) {
        Person savedPerson = personRepository.save(person);

        if (savedPerson.getName().equals("abc1"))
            throw new IllegalArgumentException("数据已存在了!");

        return savedPerson;
    }
}